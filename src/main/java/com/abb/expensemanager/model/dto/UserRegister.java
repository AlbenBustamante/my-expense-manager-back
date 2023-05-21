package com.abb.expensemanager.model.dto;

import com.abb.expensemanager.util.enums.Gender;

import java.time.LocalDate;

/**
 * The user dto model for register.
 */
public record UserRegister(String name, String lastName, String username, String email, String phone, Gender gender,
                           LocalDate birthday, String password) {
}
