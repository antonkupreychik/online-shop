package com.kupreychik.productservice.controller.rest;

import com.kupreychik.productservice.model.dto.CategoryDTO;
import com.kupreychik.productservice.model.entity.Category;
import com.kupreychik.productservice.service.CategoryService;
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
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Slf4j
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "Category", description = "Operations related to categories in the Product Service")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Get all categories", description = "Returns a paginated list of all categories available")
    public ResponseEntity<Page<Category>> getAllCategories(
            @Parameter(description = "Pagination parameters") Pageable pageable) {
        return ResponseEntity.ok(categoryService.findAllCategories(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a single category", description = "Fetch a category by its ID if it exists")
    @ApiResponse(responseCode = "200", description = "Found the category",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Category.class),
                    examples = {@ExampleObject(value = "{\"id\": 1, \"name\": \"Electronics\", \"description\": \"Electronic gadgets and devices\"}")}))
    @ApiResponse(responseCode = "404", description = "Category not found")
    public ResponseEntity<Category> getCategoryById(
            @Parameter(description = "The ID of the category to retrieve") @PathVariable Long id) {
        Category category = categoryService.findCategoryById(id);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Create a category", description = "Create a new category and return the created object")
    @ApiResponse(responseCode = "201", description = "Category created successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Category.class)))
    public ResponseEntity<Category> createCategory(
            @Parameter(description = "Category object to be created", required = true)
            @RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a category", description = "Update an existing category and return the updated object")
    @ApiResponse(responseCode = "200", description = "Category updated successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Category.class)))
    @ApiResponse(responseCode = "404", description = "Category not found")
    public ResponseEntity<Category> updateCategory(
            @Parameter(description = "The ID of the category to update") @PathVariable Long id,
            @Parameter(description = "Updated category data", required = true)
            @RequestBody CategoryDTO categoryDetails) {
        Category updatedCategory = categoryService.updateCategory(id, categoryDetails);
        return updatedCategory != null ? ResponseEntity.ok(updatedCategory) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category", description = "Delete a category by its ID and return nothing if successful")
    @ApiResponse(responseCode = "200", description = "Category deleted")
    @ApiResponse(responseCode = "404", description = "Category not found")
    public ResponseEntity<Void> deleteCategory(
            @Parameter(description = "The ID of the category to delete") @PathVariable Long id) {
        boolean isDeleted = categoryService.deleteCategory(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
