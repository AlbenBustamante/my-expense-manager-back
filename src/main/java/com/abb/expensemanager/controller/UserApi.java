package com.abb.expensemanager.controller;

import com.abb.expensemanager.model.dto.*;
import com.abb.expensemanager.service.usecase.ICategoryUseCase;
import com.abb.expensemanager.service.usecase.IReportsUseCase;
import com.abb.expensemanager.service.usecase.ITransactionUseCase;
import com.abb.expensemanager.service.usecase.IUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The user routes/endpoints.
 */
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserApi {

    private final IUserUseCase service;
    private final ICategoryUseCase categoryService;
    private final ITransactionUseCase transactionService;
    private final IReportsUseCase reportsService;

    @PostMapping(path = "/add-category")
    public ResponseEntity<UsersCategoryResponse> addCategory(@RequestBody UsersCategoryRequest request) {
        return ResponseEntity.ok(categoryService.addCategory(request));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse> get(@PathVariable("id") int id) {
        return ResponseEntity.of(service.get(id));
    }

    @GetMapping(path = "/{id}/transactions")
    public ResponseEntity<List<TransactionResponse>> getTransactions(@PathVariable("id") int id) {
        return ResponseEntity.of(transactionService.getAllByUser(id));
    }

    @GetMapping(path = "/{id}/reports")
    public ResponseEntity<ReportMainDataResponse> getReports(@PathVariable("id") int id) {
        return ResponseEntity.ok(reportsService.get(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<UserResponse> delete(@PathVariable("id") int id) {
        return service.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
