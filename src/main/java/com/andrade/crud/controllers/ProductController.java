package com.andrade.crud.controllers;

import com.andrade.crud.domain.product.Product;
import com.andrade.crud.domain.product.ProductDTO;
import com.andrade.crud.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
  @Autowired
  private ProductRepository productRepository;

  @GetMapping
  public ResponseEntity getAllProducts() {
    var allProducts = productRepository.findAllByActiveIsTrue();
    return ResponseEntity.ok(allProducts);
  }

  @PostMapping
  public ResponseEntity createProduct(@RequestBody @Valid ProductDTO data) {
    productRepository.save(new Product(data));
    return ResponseEntity.ok().build();
  }


  @PutMapping(path = "/{id}")
  @Transactional
  public ResponseEntity updateProduct(@PathVariable String id, @RequestBody @Valid ProductDTO data) {
    Optional<Product> productToBeUpdated = productRepository.findById(id);
    if (productToBeUpdated.isEmpty()) {
      throw new EntityNotFoundException();
    }
    productToBeUpdated.get().setName(data.name());
    productToBeUpdated.get().setPrice_in_cents(data.price_in_cents());
    return ResponseEntity.ok(new ProductDTO(productToBeUpdated.get()));
  }

  @DeleteMapping(path = "/{id}")
  @Transactional
  public ResponseEntity deleteProduct(@PathVariable String id) {
    Optional<Product> productToBeDeleted = productRepository.findById(id);
    if (productToBeDeleted.isEmpty()) {
      throw new EntityNotFoundException();
    }
    productToBeDeleted.get().setActive(false);
    return ResponseEntity.noContent().build();
  }

}

