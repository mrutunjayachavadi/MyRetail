package com.trg.myRetail.service;

import com.trg.myRetail.Exception.MyRetailException;
import com.trg.myRetail.model.Product;

public interface RemoteServiceClient {

    Product getProductName() throws MyRetailException;

}
