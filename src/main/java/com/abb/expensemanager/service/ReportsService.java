package com.abb.expensemanager.service;

import com.abb.expensemanager.exception.AppException;
import com.abb.expensemanager.model.dto.ReportMainDataResponse;
import com.abb.expensemanager.repository.IUserRepository;
import com.abb.expensemanager.service.usecase.IReportsUseCase;
import com.abb.expensemanager.util.enums.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * The service for the user reports.
 */
@Service
@RequiredArgsConstructor
public class ReportsService implements IReportsUseCase {

    private final IUserRepository userRepository;

    @Override
    public ReportMainDataResponse get(int userId) {
        final var userFound = userRepository.findById(userId);

        if (userFound.isEmpty()) {
            throw new AppException("The user is not found.", HttpStatus.NOT_FOUND);
        }

        final var transactions = userFound.get().getTransactions();

        final var totalCategories = userFound.get().getCategories().size();
        final var totalTransactions = transactions.size();
        final var expenses = BigDecimal.valueOf(0);
        final var incomes = BigDecimal.valueOf(0);

        transactions.forEach(transaction -> {
            if (transaction.getType().equals(TransactionType.EXPENSE)) {
                expenses.add(transaction.getValue());
            } else {
                incomes.add(transaction.getValue());
            }
        });

        final var totalExpenses = "$ " + expenses;
        final var totalIncomes = "$ " + incomes;

        return new ReportMainDataResponse(totalCategories, totalTransactions, totalExpenses, totalIncomes);
    }
}
