package com.total.august.cit.services;


import com.total.august.cit.entities.Product;
import com.total.august.cit.factory.Factory;
import com.total.august.cit.repositories.ProductRepository;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.total.august.cit.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

    @InjectMocks
    private ProductService service;

    @Mock
    private ProductRepository repository;

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

        when(repository.findAll()).thenReturn(productList);
        when(repository.findById(existingId)).thenReturn(Optional.of(product));
        doThrow(ResourceNotFoundException.class).when(repository).deleteById(nonExistingId);
    }

    @Test
    @DisplayName("findAll deve retornar uma lista de produtos")
    public void findAllShouldReturnProductList(){
        assertEquals(repository.findAll(), productList);
    }

    @Test
    @DisplayName("findById deve retornar um produto com o id escolhido")
    public void findByIdShouldReturnProductWhenIdExists(){
        Product result = service.findbyId(existingId);
        assertNotNull(service);
    }

    @Test
    @DisplayName("findById deve retornar uma excessao quando id nao existir")
    public void findByIdShouldThrowExceptionWhenIdNonExistis(){
        assertThrows(ResourceNotFoundException.class, () -> {
            service.findbyId(nonExistingId);
        });
    }


}
