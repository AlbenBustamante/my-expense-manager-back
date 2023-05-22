package com.abb.expensemanager.controller;

import com.abb.expensemanager.model.dto.AuthReq;
import com.abb.expensemanager.model.dto.AuthRes;
import com.abb.expensemanager.model.dto.UserRegister;
import com.abb.expensemanager.model.dto.UserResponse;
import com.abb.expensemanager.service.usecase.IAuthUseCase;
import com.abb.expensemanager.service.usecase.IUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The authentication routes/endpoints.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthApi {

    private final IUserUseCase userService;
    private final IAuthUseCase authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRegister register) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(register));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthRes> logIn(@RequestBody AuthReq req) {
        return ResponseEntity.ok(authService.logIn(req));
    }

    @PostMapping("/sign-out")
    public ResponseEntity<SignOutResponse> logOut(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token) {
        return ResponseEntity.ok(new SignOutResponse(authService.logOut(token)));
    }

    private record SignOutResponse(String message) {
    }

}
