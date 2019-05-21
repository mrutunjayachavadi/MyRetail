package com.trg.myRetail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = "com.trg.myRetail")
public class MyRetailApplication {



    public static void main(String[] args) {
        System.setProperty("RED_SKY_ENDPOINT", "https://redsky.target.com/v2/pdp/tcin/13860428?excludes=taxonomy,price,promotion,%20bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_st%20atistics");
        SpringApplication.run(MyRetailApplication.class, args);
    }
    
    @Bean
    public RestTemplate getRestTemplate() {
       return new RestTemplate();
    }
    
    
}