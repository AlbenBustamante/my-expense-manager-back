package com.abb.expensemanager.model.dto;

import com.abb.expensemanager.util.enums.TransactionType;

import java.time.LocalDateTime;

/**
 * The transaction dto model as response.
 */
public record TransactionResponse(int id, int userId, int categoryId, String description, String amount,
                                  TransactionType type, boolean isEnabled, LocalDateTime createdAt,
                                  LocalDateTime updatedAt) {
}
