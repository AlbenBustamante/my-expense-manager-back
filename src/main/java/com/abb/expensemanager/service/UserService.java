package com.abb.expensemanager.service;

import com.abb.expensemanager.exception.AppException;
import com.abb.expensemanager.mapper.UserMapper;
import com.abb.expensemanager.model.dto.UserRegister;
import com.abb.expensemanager.model.dto.UserResponse;
import com.abb.expensemanager.repository.IUserRepository;
import com.abb.expensemanager.service.usecase.IUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The user service.
 */
@Service
@RequiredArgsConstructor
public class UserService implements IUserUseCase {

    private final IUserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse create(UserRegister register) {
        if (repository.existsByUsername(register.username())) {
            throw new AppException("Already exists an user with the username: " + register.username(), HttpStatus.BAD_REQUEST);
        }

        if (repository.existsByEmail(register.email())) {
            throw new AppException("Already exists an user with the email: " + register.email(), HttpStatus.BAD_REQUEST);
        }

        if (repository.existsByPhone(register.phone())) {
            throw new AppException("Already exists and user with the phone: " + register.phone(), HttpStatus.BAD_REQUEST);
        }

        final var user = mapper.toEntity(register);
        user.setPassword(passwordEncoder.encode(register.password()));

        return mapper.toResponse(repository.save(user));
    }

    @Override
    public Optional<UserResponse> get(int id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    @Override
    public boolean delete(int id) {
        final var user = repository.findById(id);

        if (user.isEmpty()) {
            return false;
        }

        user.get().setIsEnabled(false);
        repository.save(user.get());

        return true;
    }

}
