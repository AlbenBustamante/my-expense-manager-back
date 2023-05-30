package com.abb.expensemanager.mapper;

import com.abb.expensemanager.model.dto.CategoryRegister;
import com.abb.expensemanager.model.dto.CategoryResponse;
import com.abb.expensemanager.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * The category mapper.
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponse toResponse(Category entity);

    List<CategoryResponse> toResponses(List<Category> entities);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isEnabled", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Category toEntity(CategoryRegister register);

}
