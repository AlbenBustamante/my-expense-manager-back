package com.abb.expensemanager.service.usecase;

import com.abb.expensemanager.model.dto.CategoryRegister;
import com.abb.expensemanager.model.dto.CategoryResponse;

import java.util.Optional;

/**
 * The category uses case.
 */
public interface ICategoryUseCase {

    /**
     * Create a new category.
     *
     * @param register the dto model for register.
     * @return the category registered as response.
     */
    CategoryResponse create(CategoryRegister register);

    /**
     * Search an existing (or not) category by the name.
     *
     * @param name the name to search.
     * @return an optional of the response.
     */
    Optional<CategoryResponse> getByName(String name);

}
