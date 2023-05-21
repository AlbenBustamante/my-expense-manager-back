package com.abb.expensemanager.service.usecase;

import com.abb.expensemanager.model.dto.UserRegister;
import com.abb.expensemanager.model.dto.UserResponse;

import java.util.Optional;

/**
 * The user uses case.
 */
public interface IUserUseCase {

    /**
     * Register a new user.
     *
     * @param register the dto model to register.
     * @return the user response model.
     */
    UserResponse create(UserRegister register);

    /**
     * Search an existing user by the id.
     *
     * @param id the id to search.
     * @return an optional of the user found.
     */
    Optional<UserResponse> get(int id);

    /**
     * Disable an existing user by the id.
     *
     * @param id the id to search.
     * @return true if the user was disabled.
     */
    boolean delete(int id);

}
