package com.harshit.eshop.controller;

import com.harshit.eshop.dao.ProductDao;
import com.harshit.eshop.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1")
public class EshopController {
    private final ProductDao productDao;


    public EshopController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping(value = "/products")
    public List<Product> getProducts() throws InterruptedException {
        log.info("Calling Dao to get the products list");
        return productDao.getProductList();
    }


    @PostMapping(value = "/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product addedProduct = productDao.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
    }

    @GetMapping(value = "/products/{id}")
    public Product getProductById(@PathVariable("id") String productId) throws InterruptedException {
        log.info("Calling Dao to get the product");
        return productDao.getProductById(productId);


    }


    @DeleteMapping(value = "/products/{id}")
    public String deleteProductById(@PathVariable("id") String productId) {
        log.info("Calling ProductDao to delete the product");
        return productDao.deleteProductById(productId);
    }

    @PutMapping(value = "/products/{id}")
    public Product updateProduct(@PathVariable("id") String productId, @RequestBody Product product ) {
        log.info("Calling Dao to update the value");
        return productDao.updateProduct(productId, product);
    }

}
