package com.kupreychik.productservice.service;

import com.kupreychik.productservice.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> findAllProducts(Pageable pageable);

    Product findProductById(Long id);

    Product createProduct(Product product);

    Product updateProduct(Long id, Product productDetails);

    boolean deleteProduct(Long id);
}
