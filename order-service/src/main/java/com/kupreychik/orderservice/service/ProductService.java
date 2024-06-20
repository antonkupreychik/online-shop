package com.kupreychik.orderservice.service;

import com.kupreychik.orderservice.model.dto.ProductDto;

public interface ProductService {

    ProductDto findProductById(Long id);
}
