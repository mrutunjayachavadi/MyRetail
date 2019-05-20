package com.trg.myRetail.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.trg.myRetail.Exception.MyRetailException;
import com.trg.myRetail.model.MyRetailResponse;
import com.trg.myRetail.model.Product;
import com.trg.myRetail.service.ProductService;
import com.trg.myRetail.service.RemoteServiceClient;

@Controller
@RequestMapping(value = "/")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    RemoteServiceClient remoteServiceClient;
    
    private static final Logger logger = Logger.getLogger(ProductController.class);

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductById(@Valid @PathVariable("id") long productId) throws MyRetailException {
        try {
            logger.info(" Inside getProductById ");
            logger.debug("productId::"+productId);
            Product product = productService.getProductById(productId);
            logger.info(" Returning from getProductById :: Product fetched Successful");
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        }
        catch (MyRetailException e) {
            logger.error("Exception occured during fetching product ::"+e.getErrorMessage());
            return new ResponseEntity<MyRetailResponse>(new MyRetailResponse(e.getErrorMessage()), e.getHttpStatus());
        }
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable("id") long productId) throws MyRetailException {

        try {
            logger.info(" Inside updateProduct ");
            if (product.getCurrentPrice() != null) {
                product.setId(productId);
                MyRetailResponse response = productService.updateProduct(product);
                logger.info(" Returning from updateProduct ");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else {
                throw new MyRetailException("Please provide pricing information for a product", HttpStatus.BAD_REQUEST);
            }

        }
        catch (MyRetailException e) {
            logger.error("Exception occured while updating product ::"+e.getErrorMessage());
            return new ResponseEntity<MyRetailResponse>(new MyRetailResponse(e.getErrorMessage()), e.getHttpStatus());
        }
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveProduct(@RequestBody List<Product> products) throws MyRetailException {

        try {
            logger.info(" Inside saveProduct ");
            MyRetailResponse response = productService.saveProducts(products);
            logger.info(" Returning from saveProduct ");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (MyRetailException e) {
            logger.error("Exception occured while saving product ::"+e.getErrorMessage());
            return new ResponseEntity<MyRetailResponse>(new MyRetailResponse(e.getErrorMessage()), e.getHttpStatus());
        }
    }

    @RequestMapping(value = "/products/{id}/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductPriceBasedOnIdAndName(@PathVariable("id") long productId, @PathVariable("name") String productName) throws MyRetailException {

        try {
            logger.info(" Inside getProductPriceBasedOnIdAndName ");
            Product product = productService.getProductInfo(productId, productName);
            logger.info(" Returning from getProductPriceBasedOnIdAndName ");
            return new ResponseEntity<>(product, HttpStatus.OK);

        }
        catch (MyRetailException e) {
            logger.error("Exception occured while fetching price information ::"+e.getErrorMessage());
            return new ResponseEntity<MyRetailResponse>(new MyRetailResponse(e.getErrorMessage()), e.getHttpStatus());
        }
    }

    @RequestMapping(value = "/products/name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductName() throws MyRetailException {

        try {
            logger.info(" Inside getProductName ");
            Product product = remoteServiceClient.getProductName();
            logger.info(" Returning from getProductName ");
            return new ResponseEntity<>(product, HttpStatus.OK);

        }
        catch (MyRetailException e) {
            logger.error("Exception occured while fetching product name::"+e.getErrorMessage());
            return new ResponseEntity<MyRetailResponse>(new MyRetailResponse(e.getErrorMessage()), e.getHttpStatus());
        }
    }

}
