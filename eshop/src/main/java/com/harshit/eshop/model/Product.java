package com.harshit.eshop.model;


// POJO or what we call Model

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


@Data
@Builder                       // Creates objects (immutable) without new keyword
@AllArgsConstructor
public class Product implements Serializable {

    private String productId;
    private String name;
    private String category;
    private double price;
}
