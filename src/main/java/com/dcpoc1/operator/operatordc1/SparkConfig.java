package com.dcpoc1.operator.operatordc1;

import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

    @Bean
    public SparkSession sparkSession() {
        return SparkSession.builder().appName("JavaSparkPi3").getOrCreate();
    }

}
