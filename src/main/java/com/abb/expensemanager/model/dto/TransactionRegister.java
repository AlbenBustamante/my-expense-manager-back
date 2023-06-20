package com.abb.expensemanager.model.dto;

import java.math.BigDecimal;

/**
 * The transaction dto model for register.
 */
public record TransactionRegister(int userId, CategoryRegister category, String description, BigDecimal value) {
}
