package com.abb.expensemanager.model.dto;

import com.abb.expensemanager.util.enums.CategoryType;

/**
 * The category dto model for register.
 */
public record CategoryRegister(String name, CategoryType type) {
}
