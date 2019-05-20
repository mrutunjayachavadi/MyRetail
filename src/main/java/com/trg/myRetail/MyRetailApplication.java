package com.trg.myRetail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = "com.trg.myRetail")
public class MyRetailApplication {
//TO-DO
    // Validate name for fetching price info from nosql datastore
    //update Readme.md
    //Create a unit test document to showcase results
    //write junit
    //add comments to all important methods
    //include postman collection as a part of repository

    public static void main(String[] args) {
        System.setProperty("RED_SKY_ENDPOINT", "https://redsky.target.com/v2/pdp/tcin/13860428?excludes=taxonomy,price,promotion,%20bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_st%20atistics");
        SpringApplication.run(MyRetailApplication.class, args);
    }
    
    @Bean
    public RestTemplate getRestTemplate() {
       return new RestTemplate();
    }
    
    
}