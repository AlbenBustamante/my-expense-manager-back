package com.abb.expensemanager.model.dto;

import com.abb.expensemanager.util.enums.Gender;

/**
 * The user dto model for register.
 */
public record UserRegister(String name, String lastName, String username, String email, String phone, Gender gender,
                           String birthday, String password) {
}
