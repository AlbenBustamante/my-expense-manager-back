package com.abb.expensemanager.util.constants;

/**
 * The main security constants.
 */
public final class SecurityConstants {

    public static final String[] WHITE_LIST = {"/auth", "/auth/**", "/swagger-ui.html", "/swagger-ui", "/api-docs", "/swagger-ui/**", "/v3/api-docs/**"};

    private SecurityConstants() {
    }

}
