package com.abb.expensemanager.service.usecase;

import com.abb.expensemanager.model.dto.TransactionRegister;
import com.abb.expensemanager.model.dto.TransactionResponse;

import java.util.List;
import java.util.Optional;

/**
 * The transaction use cases.
 */
public interface ITransactionUseCase {

    /**
     * Register a new transaction.
     *
     * @param register the request dto model for register.
     * @return the dto model as response.
     */
    TransactionResponse create(TransactionRegister register);

    /**
     * Get all the transactions made by an existing user.
     *
     * @param userId the user ID to search.
     * @return an optional of the user's transactions list.
     */
    Optional<List<TransactionResponse>> getAllByUser(int userId);

}
