package com.abb.expensemanager.model.dto;

import java.time.LocalDateTime;

/**
 * The category dto model as response.
 */
public record CategoryResponse(int id, String name, boolean isEnabled, LocalDateTime createdAt,
                               LocalDateTime updatedAt) {
}
