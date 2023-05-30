package com.abb.expensemanager.model.dto;

import java.time.LocalDateTime;

/**
 * The users category's dto model as response.
 */
public record UsersCategoryResponse(int userId, CategoryResponse category, boolean isEnabled, LocalDateTime createdAt,
                                    LocalDateTime updatedAt) {
}
