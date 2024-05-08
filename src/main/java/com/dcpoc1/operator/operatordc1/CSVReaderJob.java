package com.dcpoc1.operator.operatordc1;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class CSVReaderJob {
    @Scheduled(fixedRate = 10000) // interval in milliseconds
    public void readCsvFile() {
        // Create a Spark session
        SparkSession sparkSession = SparkSession.builder()
                .appName("CSVReaderJob")
                .getOrCreate();

        // Path to the directory containing CSV files
        String csvDirectoryPath = "/opt/spark/examples/jars/csv/";

        try {
            // Read CSV files in the specified directory, assuming there's only one file to read
            Dataset<Row> csvData = sparkSession.read()
                    .format("csv")
                    .option("header", "true")  // Adjust as per your file format
                    .load(csvDirectoryPath);

            // Print the contents to the console
            csvData.show();
        } catch (Exception e) {
            // Handle exceptions during reading/processing
            System.err.println("Error while processing CSV file(s): " + e.getMessage());
            e.printStackTrace();
        }
    }
}
