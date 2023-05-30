package com.abb.expensemanager.controller;

import com.abb.expensemanager.model.dto.CategoryRegister;
import com.abb.expensemanager.model.dto.CategoryResponse;
import com.abb.expensemanager.service.usecase.ICategoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The category routes/endpoints.
 */
@RestController
@RequestMapping(path = "/categories")
@RequiredArgsConstructor
public class CategoryApi {

    private final ICategoryUseCase service;

    @PostMapping
    public ResponseEntity<CategoryResponse> register(@RequestBody CategoryRegister register) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(register));
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<CategoryResponse> getByName(@PathVariable("name") String name) {
        return ResponseEntity.of(service.getByName(name));
    }

}
