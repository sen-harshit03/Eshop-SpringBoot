package com.harshit.eshop.dao;


// Dao = Data access object = Repository
// Dto = Data transfer object = POJO Model


import com.harshit.eshop.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.*;

import static java.lang.Thread.sleep;


@Repository
@Slf4j
public class ProductDao {

    static int id = 100;
    private final Map<String, Product> productMap = new HashMap<>();

    public ProductDao() {

    }

    @Cacheable(value = "CacheDB")
     public List<Product> getProductList() throws InterruptedException {

        log.info("Calling external service to get the product list...");
        sleep(1000);
        return new ArrayList<>(productMap.values());
     }

     @CacheEvict(value = "CacheDB", allEntries = true)
     public Product addProduct(Product product) {
         product.setProductId(createAndGetId());
         productMap.put(product.getProductId(), product);
         log.info(String.valueOf(productMap.size()));
         return product;
     }

    private String createAndGetId() {
        return "P" + id++;
    }

    @Cacheable(value = "CacheDB", key = "#productId")
    public Product getProductById(String productId) throws InterruptedException {
        log.info("Calling external service to get the product");
        sleep(1000);
        return productMap.get(productId);
    }

    @CacheEvict(value = "CacheDB", key = "#productId")
    public String deleteProductById(String productId) {
        productMap.remove(productId);
        log.info(String.valueOf(productMap.size()));
        return "Deleted the product";
    }

    @CachePut(value = "CacheDB", key = "#productId")
    public Product updateProduct(String productId, Product product) {
        log.info("Updating in the database");
        Product productFromDB = productMap.get(productId);

        if(Objects.nonNull(product.getName()) && !"".equalsIgnoreCase(product.getName())) {
            productFromDB.setName(product.getName());
        }

        if(Objects.nonNull(product.getCategory()) && !"".equalsIgnoreCase(product.getCategory())) {
            productFromDB.setCategory(product.getCategory());
        }

        if(product.getPrice() != 0.0) {
            productFromDB.setPrice(product.getPrice());
        }

        productMap.put(productId, productFromDB);
        return productFromDB;
    }
}
