package com.kupreychik.productservice.controller.rest;

import com.kupreychik.productservice.model.entity.Product;
import com.kupreychik.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Tag(name = "Product", description = "Operations related to products in the Product Service")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    @Operation(summary = "Get all products", description = "Returns a paginated list of all products available")
    public ResponseEntity<Page<Product>> getAllProducts(
            @Parameter(description = "Pagination parameters") Pageable pageable) {
        return ResponseEntity.ok(productService.findAllProducts(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a single product", description = "Fetch a product by its ID if it exists")
    @ApiResponse(responseCode = "200", description = "Product found",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<Product> getProductById(
            @Parameter(description = "The ID of the product to retrieve") @PathVariable Long id) {
        Product product = productService.findProductById(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Create a product", description = "Create a new product and return the created object")
    @ApiResponse(responseCode = "201", description = "Product created successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class)))
    public ResponseEntity<Product> createProduct(
            @Parameter(description = "Product data to create a new product", required = true)
            @RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a product", description = "Update an existing product and return the updated object")
    @ApiResponse(responseCode = "200", description = "Product updated successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<Product> updateProduct(
            @Parameter(description = "The ID of the product to update") @PathVariable Long id,
            @Parameter(description = "Updated product data", required = true)
            @RequestBody Product productDetails) {
        Product updatedProduct = productService.updateProduct(id, productDetails);
        return updatedProduct != null ? ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Delete a product by its ID and return nothing if successful")
    @ApiResponse(responseCode = "200", description = "Product deleted")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "The ID of the product to delete") @PathVariable Long id) {
        boolean isDeleted = productService.deleteProduct(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
