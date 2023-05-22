package com.abb.expensemanager.security;

import com.abb.expensemanager.exception.AppException;
import com.abb.expensemanager.util.constants.SecurityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

/**
 * The main security filter with the JWT provider.
 */
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return Arrays.stream(SecurityConstants.WHITE_LIST).anyMatch(uri -> request.getRequestURI().contains(uri));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final var header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null) {
            throw new AppException("No token provided.", HttpStatus.UNAUTHORIZED);
        }

        final var token = header.split(" ");

        if (token.length != 2 || !token[0].equals("Bearer")) {
            throw new AppException("No token provided.", HttpStatus.UNAUTHORIZED);
        }

        try {
            final var auth = jwtProvider.validateToken(token[1]);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (Exception ex) {
            SecurityContextHolder.clearContext();
            throw new AppException(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }

        filterChain.doFilter(request, response);
    }

}
