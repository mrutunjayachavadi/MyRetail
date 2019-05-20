package com.trg.myRetail.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

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
@Repository
public class ProductDAOImpl implements ProductDAO {
    
    private static final Logger logger = Logger.getLogger(ProductDAOImpl.class);
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Product getProductById(long id) throws MyRetailException{
        logger.info("Inside getProductById");
        try {
            String getProductByIdQuery = "select p.id as product_id,p.name,cp.id as curr_price_id,cp.value,cp.currency from myretail.product p, "
                                         + "myretail.current_price cp where p.current_price_id = cp.id and p.id = ? ;";
            logger.debug("getProductByIdQuery::"+getProductByIdQuery);
            return  jdbcTemplate.queryForObject(getProductByIdQuery, new Object[] { id }, new ProductMapper());
        }
        catch(EmptyResultDataAccessException e)
        {
            logger.error(" Exception occured while fetching product"+e.getMessage());
            throw new MyRetailException("No product exist with the provided product Id", HttpStatus.BAD_REQUEST);
        }
        catch (DataAccessException e) {
            logger.error(" Exception occured while fetching product"+e.getMessage());
            throw new MyRetailException( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            logger.error(" Exception occured while fetching product"+e.getMessage());
            throw new MyRetailException( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
    }
    
    class ProductMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            CurrentPrice currentPrice = new CurrentPrice();
            currentPrice.setId(rs.getLong("curr_price_id"));
            currentPrice.setValue(rs.getDouble("value"));
            currentPrice.setCurrencyCode(rs.getString("currency"));
            product.setId(rs.getLong("product_id"));
            product.setName(rs.getString("name"));
            product.setCurrentPrice(currentPrice);
            logger.info(" Returning from getproductbyId");
            return product;
            
        }
    }

    @Override
    public MyRetailResponse updateProduct(Product product) throws MyRetailException {
        MyRetailResponse response = new MyRetailResponse();
        logger.info("Inside updateProduct ");
        try {
            long priceId = getPriceId(product.getId());
            logger.debug("  Updatung price information for product id ::"+product.getId());
            String updateProductQuery = "UPDATE myretail.current_price set value = ? where id = ? ;";
            logger.debug("updateProductQuery::"+updateProductQuery);
            int rowsUpdated = jdbcTemplate.update(updateProductQuery, product.getCurrentPrice().getValue(),priceId);
            if(rowsUpdated == 0)
            {
                logger.error("Invalid product Id, please provide valid product Id ");
                throw new MyRetailException("Invalid product Id, please provide valid product Id ", HttpStatus.INTERNAL_SERVER_ERROR);      
            }
            else
                response.setMessage(" Updated product price");
        }
        catch (DataAccessException e) {
            logger.error("Exception occured during updating price information for a product ");
            throw new MyRetailException("Exception occured during updating price information for a product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("  Returning from updateProduct");
        return response;
    }
    
    @Override
    public long createProduct(final Product product) throws MyRetailException{
        logger.info("  Inside createProduct");
        Number productId= 0;
        try {
            String createProductQuery = "INSERT INTO myretail.product(name,current_price_id)  VALUES (?,?);";
            logger.debug("createProductQuery::"+createProductQuery);
            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(createProductQuery,new String[] {"id"});
                    ps.setString(1, product.getName());
                    ps.setLong(2, product.getCurrentPrice().getId());
                    return ps;
                }
            }, holder);
            productId = holder.getKey();
        }
        catch (DataAccessException e) {
            logger.error("Exception occured during while saving product"+e.getMessage());
            throw new MyRetailException("Exception occured during while saving product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("Returning from createProduct");
        return productId.longValue();
    }
    
    
    @Override
    public long saveCurrentPrice(final CurrentPrice currentPrice) throws MyRetailException {
        Number productId;
        try {
            logger.info("  Inside saveCurrentPrice");
            String createCurrentPriceQuery = "INSERT INTO myretail.current_price (value,currency)  VALUES (?,?);";
            logger.debug("createCurrentPriceQuery::"+createCurrentPriceQuery);
            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(createCurrentPriceQuery,new String[] {"id"});
                    ps.setDouble(1, currentPrice.getValue());
                    ps.setString(2, currentPrice.getCurrencyCode());
                    return ps;
                }
            }, holder);

            productId = holder.getKey();
            logger.info(" Returning from saveCurrentPrice");
        }
        catch (DataAccessException e) {
            logger.error("Exception occured during while saving price info"+e.getMessage());
            throw new MyRetailException("Exception occured during while saving price info", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return productId.longValue();
    }
    
    
    private long getPriceId(long ProductId) throws MyRetailException{
        try {
            logger.info(" Inside getPriceId");
            String getProductByIdQuery = "SELECT p.current_price_id from myretail.product p where p.id = ?;";
            logger.debug("getProductByIdQuery::"+getProductByIdQuery);
            return  this.jdbcTemplate.queryForObject(getProductByIdQuery, new Object[] { ProductId },Long.class);
        }
        catch (DataAccessException e) {
            logger.error("No product exist with the provided product Id"+e.getMessage());
            throw new MyRetailException( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            logger.error("No product exist with the provided product Id"+e.getMessage());
            throw new MyRetailException( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
    }

}