package com.abb.expensemanager.service;

import com.abb.expensemanager.exception.AppException;
import com.abb.expensemanager.model.dto.MainStatsReportResponse;
import com.abb.expensemanager.model.dto.ReportsResponse;
import com.abb.expensemanager.model.entity.User;
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
    public ReportsResponse get(int userId) {
        final var userFound = userRepository.findById(userId);

        if (userFound.isEmpty()) {
            throw new AppException("The user is not found.", HttpStatus.NOT_FOUND);
        }

        final var mainStats = generateMainStats(userFound.get());

        final var currentBalance = "0";

        return new ReportsResponse(mainStats, currentBalance);
    }

    private MainStatsReportResponse generateMainStats(final User user) {
        final var transactions = user.getTransactions();

        final var totalCategories = user.getCategories().size();
        final var totalTransactions = transactions.size();
        var expenses = BigDecimal.valueOf(0);
        var incomes = BigDecimal.valueOf(0);

        for (final var transaction : transactions) {
            if (transaction.getType().equals(TransactionType.EXPENSE)) {
                expenses = expenses.add(transaction.getValue());
            } else {
                incomes = incomes.add(transaction.getValue());
            }
        }

        final var totalExpenses = "$ " + expenses;
        final var totalIncomes = "$ " + incomes;

        return new MainStatsReportResponse(totalCategories, totalTransactions, totalExpenses, totalIncomes);
    }
}
