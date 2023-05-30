package com.abb.expensemanager.service;

import com.abb.expensemanager.exception.AppException;
import com.abb.expensemanager.mapper.CategoryMapper;
import com.abb.expensemanager.model.dto.CategoryRegister;
import com.abb.expensemanager.model.dto.CategoryResponse;
import com.abb.expensemanager.repository.ICategoryRepository;
import com.abb.expensemanager.service.usecase.ICategoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The service category.
 */
@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryUseCase {

    private final ICategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public CategoryResponse create(CategoryRegister register) {
        if (repository.findByName(register.name()).isPresent()) {
            throw new AppException("This category already exists", HttpStatus.BAD_REQUEST);
        }

        return mapper.toResponse(repository.save(mapper.toEntity(register)));
    }

    @Override
    public Optional<CategoryResponse> getByName(String name) {
        return repository.findByName(name).map(mapper::toResponse);
    }

}
