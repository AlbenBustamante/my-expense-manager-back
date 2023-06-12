package com.abb.expensemanager.controller;

import com.abb.expensemanager.model.dto.TransactionRegister;
import com.abb.expensemanager.model.dto.TransactionResponse;
import com.abb.expensemanager.service.usecase.ITransactionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
