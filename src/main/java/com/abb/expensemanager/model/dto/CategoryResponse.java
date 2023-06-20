package com.abb.expensemanager.model.dto;

import com.abb.expensemanager.util.enums.CategoryType;

import java.time.LocalDateTime;

/**
 * The category dto model as response.
 */
public record CategoryResponse(int id, String name, CategoryType type, boolean isEnabled, LocalDateTime createdAt,
                               LocalDateTime updatedAt) {
}
