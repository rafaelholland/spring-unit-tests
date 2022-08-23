package com.total.august.cit.factory;

import com.total.august.cit.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class Factory {

    public static Product createProduct(){
        return new Product(1L, "iPhone", "Um bom telefone", 10000.00);
    }

    public static List<Product> createProductList(){
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product(1L, "iPhone", "Um bom telefone", 10000.00);
        Product product2 = new Product(2L, "Mesa", "Um boa mesa", 10000.00);
        productList.add(product1);
        productList.add(product2);

        return productList;
    }
}
