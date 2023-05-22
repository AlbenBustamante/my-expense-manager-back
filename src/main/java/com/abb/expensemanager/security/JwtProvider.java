package com.abb.expensemanager.security;

import com.abb.expensemanager.model.dto.UserResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * The JWT utilities.
 */
@Component
public class JwtProvider {

    @Value("${jwt.secret.key}")
    private String secretKey;

    private final Map<String, UserResponse> tokens = new HashMap<>();

    /**
     * Generate a new token and add to tokens list.
     *
     * @param user the user logged.
     * @return the token generated.
     */
    public String createToken(final UserResponse user) {
        final var date = new Date();

        final var token = JWT.create()
                .withSubject(user.username())
                .withClaim("id", user.id())
                .withClaim("email", user.email())
                .withClaim("phone", user.phone())
                .withIssuedAt(date)
                .withExpiresAt(new Date(date.getTime() + 28800000))
                .sign(Algorithm.HMAC256(secretKey));

        tokens.put(token, user);

        return token;
    }

    /**
     * Validates the token and gets the user session.
     *
     * @param token the token to be verified.
     * @return a {@link UsernamePasswordAuthenticationToken} as the user {@link Authentication}
     */
    public Authentication validateToken(final String token) {
        JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);

        final var user = tokens.get(token);

        if (user == null) {
            throw new BadCredentialsException("The user doesn't exists.");
        }

        final Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.role()));

        return new UsernamePasswordAuthenticationToken(user, token, authorities);
    }

    /**
     * Remove the token from tokens list.
     *
     * @param token the token to be removed.
     * @return a confirmation message.
     */
    public String deleteToken(final String token) {
        if (!tokens.containsKey(token)) {
            return "The token doesn't exists!";
        }

        tokens.remove(token);

        return "Sign out successfully!";
    }

}
