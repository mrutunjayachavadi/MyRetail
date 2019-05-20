package com.trg.myRetail.dao;

import com.trg.myRetail.Exception.MyRetailException;
import com.trg.myRetail.model.CurrentPrice;
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
public interface ProductDAO {
    
    Product getProductById(long id) throws MyRetailException;

    MyRetailResponse updateProduct(Product product) throws MyRetailException;

    long createProduct(Product product) throws MyRetailException;

    long saveCurrentPrice(CurrentPrice currentPrice) throws MyRetailException;

}