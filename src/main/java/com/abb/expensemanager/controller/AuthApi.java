package com.abb.expensemanager.controller;

import com.abb.expensemanager.model.dto.UserRegister;
import com.abb.expensemanager.model.dto.UserResponse;
import com.abb.expensemanager.service.usecase.IUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The authentication routes/endpoints.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthApi {

    private final IUserUseCase service;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRegister register) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(register));
    }

}
