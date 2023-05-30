package com.abb.expensemanager.model.dto;

import com.abb.expensemanager.util.enums.TransactionType;

import java.math.BigDecimal;

/**
 * The transaction dto model for register.
 */
public record TransactionRegister(int userId, String categoryName, String description, BigDecimal value,
                                  TransactionType type) {
}
