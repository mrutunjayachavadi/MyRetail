package com.trg.myRetail.service;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.trg.myRetail.Exception.MyRetailException;
import com.trg.myRetail.model.Product;


@Service
public class RemoteServiceClientImpl implements RemoteServiceClient{
    
    private static final Logger logger = Logger.getLogger(RemoteServiceClientImpl.class);
    
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Product getProductName() throws MyRetailException{
        logger.info(" Inside getProductName");
        String title = null;
        Product product = new Product();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        try {
            HttpEntity <String> entity = new HttpEntity<String>(headers);
            logger.info("  Fetching product name from remote url");
            String responseEntity = restTemplate.exchange(System.getProperty("RED_SKY_ENDPOINT"), HttpMethod.GET, entity, String.class).getBody();
            if (responseEntity != null) {

                JsonObject jsonResponse = new JsonParser().parse(responseEntity).getAsJsonObject();
                title = jsonResponse.getAsJsonObject("product").getAsJsonObject("item").getAsJsonObject("product_description").get("title").toString();
                title = title.replace("\"","");
                logger.debug("\n------- Title :: " + title + " --------");
                product.setName(title);
            }
        }
        catch (RestClientException | JsonSyntaxException e) {
            logger.error(" Exception occured while fetching produt name from remote url");
            throw new MyRetailException("Exception occured while fetching produt name from remote url", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info(" Returning from getProductName");
        return product;
    }
}