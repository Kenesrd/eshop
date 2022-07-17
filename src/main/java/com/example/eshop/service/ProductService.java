package com.example.eshop.service;

import com.example.eshop.dto.ProductDto;

import java.util.List;


public interface ProductService {
    List<ProductDto> getAll();
    void addToUserBucket(Long productId,String username);
    void addProduct(ProductDto dto);
    ProductDto getById(Long id);
}
