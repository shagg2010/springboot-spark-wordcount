package com.yadas.web.spark.wordcount.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class SparkConfig {

    @Value("${spark.app.name}")
    private String appName;
    @Value("${spark.master}")
    private String masterUri;

    @Bean
    @Lazy
    public SparkConf conf() {
        return new SparkConf().setAppName(appName).setMaster(masterUri);
    }

    @Bean
    @Lazy
    public JavaSparkContext sc() {
        return new JavaSparkContext(conf());
    }
}
