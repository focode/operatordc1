/* Apache 2 GPL */

package com.dcpoc1.operator.operatordc1;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.apache.spark.internal.Logging;


@Component
public class CSVReaderJob {
    @Scheduled(fixedRate = 10000) // interval in milliseconds
    public void readCsvFile() {
        Logger logger = LoggerFactory.getLogger(CSVReaderJob.class);
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

            String inferSchemaJson = csvData.schema().json();
            System.out.println("Schema should be printed1");
            System.out.println("Schema: " + inferSchemaJson);
            logger.info("Schema should be printed2 {}", inferSchemaJson);
            // Print the contents to the console
            csvData.show();
            csvData.printSchema(); // Ensure schema is printed in the Spark log
        } catch (Exception e) {
            // Handle exceptions during reading/processing
            System.err.println("Error while processing CSV file(s): " + e.getMessage());
            e.printStackTrace();
        }
    }
}
