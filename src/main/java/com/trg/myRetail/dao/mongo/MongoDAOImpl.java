package com.trg.myRetail.dao.mongo;
 
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.trg.myRetail.Exception.MyRetailException;
import com.trg.myRetail.model.MyRetailResponse;
import com.trg.myRetail.model.mongo.CurrPrice;
 
@Repository
public class MongoDAOImpl implements MongoDAO{
    
    private static final Logger logger = Logger.getLogger(MongoDAOImpl.class);
    
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public MyRetailResponse savePriceInfo(CurrPrice currentPrice) throws MyRetailException {
        logger.info(" Inside savePriceInfo");
        MyRetailResponse response = new MyRetailResponse();
        try {
            mongoTemplate.save(currentPrice);
            response.setMessage("Successfully saved price info into mongo");
        }
        catch (Exception e) {
            logger.error("Exception occured while saving price info"+e.getMessage());
            throw new MyRetailException("Exception occured while saving price info", HttpStatus.BAD_REQUEST);
        }
        logger.info(" Returning from savePriceInfo");
        return response;
        
    }

    @Override
    public CurrPrice getPriceInfo(long productId) throws MyRetailException {
        try {
            logger.info(" Inside savePriceInfo");
            Query query = new Query(Criteria.where("productId").is(productId));
            return mongoTemplate.findOne(query, CurrPrice.class);
        }
        catch (Exception e) {
            logger.error("Exception occured while fetching price info"+e.getMessage());
            throw new MyRetailException("Exception occured while fetching price info", HttpStatus.BAD_REQUEST);
        }
        
    }
 
}