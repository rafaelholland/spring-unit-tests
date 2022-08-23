package com.total.august.cit.services;

import com.total.august.cit.entities.Product;
import com.total.august.cit.repositories.ProductRepository;
import com.total.august.cit.services.exceptions.DataBaseException;
import com.total.august.cit.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional
    public List<Product> findAll(){
        return repository.findAll();
    }

    @Transactional
    public Product findbyId(Long id){
        Optional<Product> product = repository.findById(id);
        return product.orElseThrow(() -> new ResourceNotFoundException("Entidade nao encontrada."));
    }

    @Transactional
    public Product save(Product product){
        repository.save(product);
        return product;
    }

    public void delete(Long id) {
        try{
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id not found" + id);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException("Integrity violation");
        }
    }

}
