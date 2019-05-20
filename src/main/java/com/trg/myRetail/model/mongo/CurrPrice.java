package com.trg.myRetail.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="current_price")
public class CurrPrice {
    
    @Id
    private long id;
    
    private long  productId;
    
    private Double value;
    private String currencyCode;

    
    /**
     * @return the value
     */
    public Double getValue() {
        return value;
    }
    
    /**
     * @return the currencyCode
     */
    public String getCurrencyCode() {
        return currencyCode;
    }
    
    /**
     * @param value the value to set
     */
    public void setValue(Double value) {
        this.value = value;
    }
    
    /**
     * @param currencyCode the currencyCode to set
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the productId
     */
    public long getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(long productId) {
        this.productId = productId;
    }
    
    

}
