package com.total.august.cit.repositories;


import com.total.august.cit.entities.Product;
import com.total.august.cit.factory.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProductRepositoryTests {


    @Autowired
    private ProductRepository repository;

    private Long existingId;
    private Long nonExistingId;
    private Long countTotalProducts;

    @BeforeEach
    void setUp() throws Exception{
        existingId = 1L;
        nonExistingId = 1000L;
        countTotalProducts = 3L;
    }

    @Test
    public void salvarDeveriaAutoIncrementQuandoIdNulo(){
        Product product = Factory.createProduct();
        product.setId(null);

        product = repository.save(product);

        assertNotNull(product.getId());
        assertEquals(countTotalProducts + 1, product.getId());
    }

    @Test
    public void aoDeletarRegistroDeveSeTornarInexistente(){
        repository.deleteById(1L);
        Optional<Product> result = repository.findById(existingId);

        assertFalse(result.isPresent());
    }



}
