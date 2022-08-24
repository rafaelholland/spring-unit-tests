package com.total.august.cit.controller;

import com.total.august.cit.controllers.ProductController;
import com.total.august.cit.entities.Product;
import com.total.august.cit.factory.Factory;
import com.total.august.cit.services.ProductService;
import com.total.august.cit.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

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

        when(service.findAll()).thenReturn(productList);
        when(service.findbyId(existingId)).thenReturn(product);
        when(service.findbyId(nonExistingId)).thenThrow(ResourceNotFoundException.class);

    }

    @Test
    @DisplayName("findAll deve retornar um status 200 sempre")
    public void findAllShouldReturnStatus200() throws Exception {
       ResultActions result =
               mockMvc.perform(get("/products")
                       .accept(MediaType.APPLICATION_JSON));

       result.andExpect(status().isOk());
    }

    @Test
    @DisplayName("findById deve retornar um produto quando inserido um id existente")
    public void findByIdShouldReturnProductWhenIdExists() throws Exception {
        ResultActions result =
                mockMvc.perform(get("/products/{id}", existingId)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").exists());
        result.andExpect(jsonPath("$.description").exists());
        result.andExpect(jsonPath("$.price").exists());

    }

    @Test
    @DisplayName("findById deve retornar uma excessao ResourceNotFound quando o id nao existir")
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() throws Exception {
        ResultActions result =
                mockMvc.perform(get("/products/{id}", nonExistingId)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());

    }

}
