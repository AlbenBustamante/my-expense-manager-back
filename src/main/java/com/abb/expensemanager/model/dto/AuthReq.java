package com.abb.expensemanager.model.dto;

/**
 * The authentication dto model for login.
 */
public record AuthReq(String usernameOrEmailOrPhone, String password) {
}
