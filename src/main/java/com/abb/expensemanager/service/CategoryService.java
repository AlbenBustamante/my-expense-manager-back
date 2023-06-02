package com.abb.expensemanager.service;

import com.abb.expensemanager.exception.AppException;
import com.abb.expensemanager.mapper.CategoryMapper;
import com.abb.expensemanager.mapper.UsersCategoryMapper;
import com.abb.expensemanager.model.dto.CategoryRegister;
import com.abb.expensemanager.model.dto.CategoryResponse;
import com.abb.expensemanager.model.dto.UsersCategoryRequest;
import com.abb.expensemanager.model.dto.UsersCategoryResponse;
import com.abb.expensemanager.model.entity.Category;
import com.abb.expensemanager.repository.ICategoryRepository;
import com.abb.expensemanager.repository.IUserRepository;
import com.abb.expensemanager.repository.IUsersCategoryRepository;
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
    private final IUserRepository userRepository;
    private final IUsersCategoryRepository usersCategoryRepository;
    private final UsersCategoryMapper usersCategoryMapper;

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

    @Override
    public UsersCategoryResponse addCategory(UsersCategoryRequest request) {
        final var userFound = userRepository.findById(request.userId());

        if (userFound.isEmpty()) {
            throw new AppException("The user is not found.", HttpStatus.NOT_FOUND);
        }

        final var categoryFound = repository.findByName(request.categoryName());
        var newCategory = new Category();

        if (categoryFound.isEmpty()) {
            newCategory.setName(request.categoryName());
            newCategory = repository.save(newCategory);
        }

        final var usersCategory = usersCategoryMapper.toEntity(request);
        usersCategory.getId().setCategoryId(categoryFound.isPresent() ? categoryFound.get().getId() : newCategory.getId());

        return usersCategoryMapper.toResponse(usersCategoryRepository.save(usersCategory));
    }

}
