package com.abb.expensemanager.controller;

import com.abb.expensemanager.model.dto.TransactionRegister;
import com.abb.expensemanager.model.dto.TransactionResponse;
import com.abb.expensemanager.service.usecase.ITransactionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The transaction routes/endpoints.
 */
@RestController
@RequestMapping(path = "/transactions")
@RequiredArgsConstructor
public class TransactionApi {

    private final ITransactionUseCase service;

    @PostMapping
    public ResponseEntity<TransactionResponse> register(@RequestBody TransactionRegister register) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(register));
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<List<TransactionResponse>> getAllByUser(@PathVariable("userId") int userId) {
        return ResponseEntity.of(service.getAllByUser(userId));
    }

}
