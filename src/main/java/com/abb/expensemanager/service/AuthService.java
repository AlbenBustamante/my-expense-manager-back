package com.abb.expensemanager.service;

import com.abb.expensemanager.exception.AppException;
import com.abb.expensemanager.mapper.UserMapper;
import com.abb.expensemanager.model.dto.AuthReq;
import com.abb.expensemanager.model.dto.AuthRes;
import com.abb.expensemanager.repository.IUserRepository;
import com.abb.expensemanager.security.JwtProvider;
import com.abb.expensemanager.service.usecase.IAuthUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The authentication service.
 */
@Service
@RequiredArgsConstructor
public class AuthService implements IAuthUseCase {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final UserMapper userMapper;

    @Override
    public AuthRes logIn(AuthReq req) {
        final var user = userRepository.findByUsernameOrEmailOrPhone(req.usernameOrEmailOrPhone(), req.usernameOrEmailOrPhone(), req.usernameOrEmailOrPhone());

        if (user.isEmpty() || !passwordEncoder.matches(req.password(), user.get().getPassword())) {
            throw new AppException("The user doesn't exists...", HttpStatus.BAD_REQUEST);
        }

        return new AuthRes(user.get().getId(), jwtProvider.createToken(userMapper.toResponse(user.get())));
    }

    @Override
    public String logOut(String token) {
        final var elements = token.split(" ");

        return jwtProvider.deleteToken(elements[1]);
    }

}
