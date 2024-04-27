package com.dcpoc1.operator.operatordc1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.apache.spark.sql.SparkSession;
import java.io.IOException;
import java.nio.file.*;
import java.time.Instant;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

@Component
public class FileConvertorJob {

    @Autowired
    SparkSession sparkSession;

    @Scheduled(fixedRate = 10000) // interval in milliseconds
    public void performTask() {
        System.out.println("Task performed at " + Instant.now());
        Path dirPath = Paths.get("/opt/spark/examples/jars/csv/");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath, "*.csv")) {
            boolean foundCSV = false;
            for (Path filePath : stream) {
                foundCSV = true;
                System.out.println("Found CSV file: " + filePath.getFileName());
                printFileContents(filePath, sparkSession);
            }
            if (!foundCSV) {
                System.out.println("No CSV files found in the directory.");
            }
        } catch (IOException e) {
            System.err.println("Error reading directory: " + e.getMessage());
        }
    }

    private static void printFileContents(Path filePath, SparkSession spark) throws IOException {
        Dataset<Row> csvData = spark.read().format("csv").option("header", "true").load("/opt/spark/examples/jars/csv");
        String outputFileName = "/opt/spark/examples/jars/par/"+Instant.now().toEpochMilli() + ".parquet";
        csvData.write().mode("overwrite").parquet(outputFileName);
        Files.delete(filePath);
        System.out.println("deleted.");
    }

}
