package com.total.august.cit.controller;

import com.total.august.cit.entities.Product;
import com.total.august.cit.factory.Factory;
import com.total.august.cit.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductControllerIT {


    @Autowired
    private MockMvc mockMvc;

    private long existingId;
    private long nonExistingId;
    private Product product;
    List<Product> productList = new ArrayList<>();

    @BeforeEach
    void setUp() throws Exception{

        existingId = 1L;
        nonExistingId = 10L;

        product = Factory.createProduct();
        productList = Factory.createProductList();


    }

    @Test
    public void findAllShouldReturnAllProducts() throws Exception{




    }



}
