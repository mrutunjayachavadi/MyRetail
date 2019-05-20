package com.trg.myRetail.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.trg.myRetail.Exception.MyRetailException;
import com.trg.myRetail.model.CurrentPrice;
import com.trg.myRetail.model.Product;
import com.trg.myRetail.service.ProductServiceImpl;

@RunWith(PowerMockRunner.class)
public class ProductControllerTest {

   @Autowired
   MockMvc mvc;
   
   @InjectMocks
   ProductController productController = new ProductController();
   
   @Mock
   private ProductServiceImpl productServiceImpl;


   Gson gson;

   @BeforeClass
   public static void setUp() {
   }

   @Before
   public void setUpBeforeEachTest() {
      MockitoAnnotations.initMocks(this);
      gson = new Gson();
   }


   @Test
   public void testGetProductId() throws Exception {
      Product product = createProduct();
      PowerMockito.when(productServiceImpl.getProductById(108888L)).thenReturn(product);
      ResponseEntity<?> responseEntity = productController.getProductById(product.getId());
      assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
      assertEquals(product, responseEntity.getBody());
   }
   
   @Test
   public void testGetProductIdWithException() throws Exception {
       Product product = createProduct();
       PowerMockito.when(productServiceImpl.getProductById(10)).thenThrow(MyRetailException.class);
       productController.getProductById(product.getId());
       //assertEquals(null, responseEntity.getBody());
   }

private Product createProduct() {
    CurrentPrice currentPrice = new CurrentPrice();
    Product product = new Product();
    currentPrice.setCurrencyCode("USD");
    currentPrice.setValue(10.00);
    product.setId(108888L);
    product.setName("My Product1");
    product.setCurrentPrice(currentPrice);
    return product;
}

}