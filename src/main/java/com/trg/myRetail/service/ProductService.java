package com.trg.myRetail.service;

import java.util.List;

import com.trg.myRetail.Exception.MyRetailException;
import com.trg.myRetail.model.MyRetailResponse;
import com.trg.myRetail.model.Product;

/**
 *
 * @author 310241499
 * @version
 * @since 
 *
 *
 */
public interface ProductService {
    
    Product getProductById(long id) throws MyRetailException;

    MyRetailResponse updateProduct(Product product) throws MyRetailException;
    
    MyRetailResponse saveProducts(List<Product> products) throws MyRetailException ;
    
    Product getProductInfo(long productId, String productName) throws MyRetailException;
}
