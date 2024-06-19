package com.kupreychik.productservice.mapper;

import com.kupreychik.productservice.model.dto.CategoryDTO;
import com.kupreychik.productservice.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    void updateCategoryFromDto(CategoryDTO dto, @MappingTarget Category entity);
}