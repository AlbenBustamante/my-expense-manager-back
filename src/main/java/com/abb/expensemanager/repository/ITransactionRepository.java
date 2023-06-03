package com.abb.expensemanager.repository;

import com.abb.expensemanager.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * The {@link Transaction} repository.
 */
public interface ITransactionRepository extends JpaRepository<Transaction, Integer> {

    /**
     * Find all the transactions made by and user.
     *
     * @param userId the user ID to search.
     * @return an optional of the user's transactions list.
     */
    Optional<List<Transaction>> findAllByUserId(int userId);

}
