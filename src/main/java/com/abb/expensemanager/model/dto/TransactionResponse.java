package com.abb.expensemanager.model.dto;

import java.time.LocalDateTime;

/**
 * The transaction dto model as response.
 */
public record TransactionResponse(int id, int userId, CategoryResponse category, String description, String value,
                                  boolean isEnabled, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
