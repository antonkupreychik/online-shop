package com.kupreychik.productservice.service;

import com.kupreychik.productservice.model.dto.CategoryDTO;
import com.kupreychik.productservice.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<Category> findAllCategories(Pageable pageable);

    Category findCategoryById(Long id);

    Category createCategory(Category category);

    Category updateCategory(Long id, CategoryDTO categoryDTO);

    boolean deleteCategory(Long id);

}
