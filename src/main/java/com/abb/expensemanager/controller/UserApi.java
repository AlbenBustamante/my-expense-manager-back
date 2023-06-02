package com.abb.expensemanager.controller;

import com.abb.expensemanager.model.dto.UserResponse;
import com.abb.expensemanager.model.dto.UsersCategoryRequest;
import com.abb.expensemanager.model.dto.UsersCategoryResponse;
import com.abb.expensemanager.service.usecase.ICategoryUseCase;
import com.abb.expensemanager.service.usecase.IUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The user routes/endpoints.
 */
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserApi {

    private final IUserUseCase service;
    private final ICategoryUseCase categoryService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse> get(@PathVariable("id") int id) {
        return ResponseEntity.of(service.get(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<UserResponse> delete(@PathVariable("id") int id) {
        return service.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/add-category")
    public ResponseEntity<UsersCategoryResponse> addCategory(@RequestBody UsersCategoryRequest request) {
        return ResponseEntity.ok(categoryService.addCategory(request));
    }

}
