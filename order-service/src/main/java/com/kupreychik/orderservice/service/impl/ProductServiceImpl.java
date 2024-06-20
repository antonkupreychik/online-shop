package com.kupreychik.orderservice.service.impl;

import com.kupreychik.orderservice.config.ProductServiceProperties;
import com.kupreychik.orderservice.model.dto.ProductDto;
import com.kupreychik.orderservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductServiceProperties properties;

    @Override
    public ProductDto findProductById(Long id) {
        RestClient restClient = RestClient.create();
        return restClient.get()
                .uri(properties.getUrl() + properties.getPath() + "/" + id)
                .retrieve()
                .body(ProductDto.class);
    }
}
