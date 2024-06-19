package com.kupreychik.productservice.service.impl;

import com.kupreychik.productservice.model.entity.Product;
import com.kupreychik.productservice.repository.ProductRepository;
import com.kupreychik.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Page<Product> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long id) {
        return false;
    }
}
