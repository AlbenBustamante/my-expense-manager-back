package com.abb.expensemanager.service.usecase;

import com.abb.expensemanager.model.dto.AuthReq;
import com.abb.expensemanager.model.dto.AuthRes;

/**
 * The authentication uses case.
 */
public interface IAuthUseCase {

    /**
     * Verifies the data provided and generate a new token for the user logged.
     *
     * @param req the data to validate.
     * @return the user id and token generated.
     */
    AuthRes logIn(AuthReq req);

    /**
     * Removes the token from the tokens list.
     *
     * @param token the token to be removed.
     * @return a confirmation message.
     */
    String logOut(String token);

}
