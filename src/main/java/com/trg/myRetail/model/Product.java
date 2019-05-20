package com.trg.myRetail.model;

public class Product {
    
    private long id;
    private String name;
    private CurrentPrice currentPrice;
    
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
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return the currentPrice
     */
    public CurrentPrice getCurrentPrice() {
        return currentPrice;
    }
    
    /**
     * @param currentPrice the currentPrice to set
     */
    public void setCurrentPrice(CurrentPrice currentPrice) {
        this.currentPrice = currentPrice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", currentPrice=" + currentPrice + "]";
    }

}
