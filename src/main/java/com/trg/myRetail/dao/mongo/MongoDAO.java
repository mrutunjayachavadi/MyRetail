package com.trg.myRetail.dao.mongo;

import com.trg.myRetail.Exception.MyRetailException;
import com.trg.myRetail.model.MyRetailResponse;
import com.trg.myRetail.model.mongo.CurrPrice;

public interface MongoDAO{
    
    MyRetailResponse savePriceInfo(CurrPrice currentPriceMongo) throws MyRetailException;
    CurrPrice getPriceInfo(long productId) throws MyRetailException;
 
}