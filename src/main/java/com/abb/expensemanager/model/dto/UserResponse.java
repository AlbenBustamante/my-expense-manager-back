package com.abb.expensemanager.model.dto;

import com.abb.expensemanager.util.enums.Gender;
import com.abb.expensemanager.util.enums.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * The user dto model as response.
 */
public record UserResponse(int id, String name, String lastName, String username, String email, String phone,
                           Gender gender, LocalDate birthday, Role role, Set<CategoryResponse> categories,
                           boolean isEnabled, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
