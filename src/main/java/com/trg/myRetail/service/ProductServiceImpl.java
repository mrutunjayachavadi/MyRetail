package com.trg.myRetail.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.trg.myRetail.Exception.MyRetailException;
import com.trg.myRetail.dao.ProductDAO;
import com.trg.myRetail.dao.mongo.MongoDAO;
import com.trg.myRetail.model.CurrentPrice;
import com.trg.myRetail.model.MyRetailResponse;
import com.trg.myRetail.model.Product;
import com.trg.myRetail.model.mongo.CurrPrice;

/**
 *
 * @author 310241499
 * @version
 * @since
 *
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDAO productDAO;
    
    @Autowired
    MongoDAO mongoDAO;
    
    private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);


    /**
     * {@inheritDoc}
     */
    public Product getProductById(long id) throws MyRetailException {
        return productDAO.getProductById(id);
    }

    @Override
    public MyRetailResponse updateProduct(Product product) throws MyRetailException {
        return productDAO.updateProduct(product);
    }
    

    @Override
    public MyRetailResponse saveProducts(List<Product> products) throws MyRetailException {
        logger.info(" Inside  saveProducts");
        MyRetailResponse response = new MyRetailResponse();
        long productId = 0L;
        long currentPriceId = 0L;
        try {
            for(Product product:products)
            {
                currentPriceId = productDAO.saveCurrentPrice(product.getCurrentPrice());
                product.getCurrentPrice().setId(currentPriceId);
                productId = productDAO.createProduct(product);
                product.setId(productId);
                
                CurrPrice currPrice = new CurrPrice();
                currPrice.setId(currentPriceId);
                currPrice.setCurrencyCode(product.getCurrentPrice().getCurrencyCode());
                currPrice.setValue(product.getCurrentPrice().getValue());
                currPrice.setProductId(productId);
                mongoDAO.savePriceInfo(currPrice);
            }

            response.setMessage("Successfully saved price info into mongo");
        }
        catch (Exception e) {
            logger.error("Exception occured while saving product");
            throw new MyRetailException("Exception occured while saving product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info(" Returning from  saveProducts");
        return response;
    }

    @Override
    public Product getProductInfo(long productId, String productName) throws MyRetailException {
        logger.info(" Inside  getProductInfo");
        Product product;
        try {
            product = new Product();
            CurrentPrice currentPrice = new CurrentPrice();
            product.setId(productId);
            product.setName(productName);
            CurrPrice currPrice = mongoDAO.getPriceInfo(productId);
            if(currPrice != null)
            {
                currentPrice.setCurrencyCode(currPrice.getCurrencyCode());
                currentPrice.setValue(currPrice.getValue());
                product.setCurrentPrice(currentPrice);
            }
            else
            {
                logger.error("Invalid product Id  ");
                throw new MyRetailException("Invalid product Id ", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            logger.info(" Returning from  getProductInfo");
        }
        catch (MyRetailException e) {
            logger.error("Exception occured while fetching  product info");
            throw new MyRetailException("Exception occured while fetching  product info", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return product;
    }

}
