package com.abb.expensemanager.service;

import com.abb.expensemanager.exception.AppException;
import com.abb.expensemanager.model.dto.MainStatsReportResponse;
import com.abb.expensemanager.model.dto.ReportsResponse;
import com.abb.expensemanager.model.entity.User;
import com.abb.expensemanager.repository.IUserRepository;
import com.abb.expensemanager.service.usecase.IReportsUseCase;
import com.abb.expensemanager.util.CurrencyConverter;
import com.abb.expensemanager.util.enums.CategoryType;
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
    private final CurrencyConverter currencyConverter;

    @Override
    public ReportsResponse get(int userId) {
        final var userFound = userRepository.findById(userId);

        if (userFound.isEmpty()) {
            throw new AppException("The user is not found.", HttpStatus.NOT_FOUND);
        }

        final var mainStats = generateMainStats(userFound.get());

        final var expenses = currencyConverter.toBigDecimal(mainStats.totalExpenses());
        final var incomes = currencyConverter.toBigDecimal(mainStats.totalIncomes());

        final var currentBalance = BigDecimal.ZERO
                .subtract(expenses)
                .add(incomes);

        return new ReportsResponse(mainStats, currencyConverter.toString(currentBalance));
    }

    private MainStatsReportResponse generateMainStats(final User user) {
        final var transactions = user.getTransactions();

        final var totalCategories = user.getCategories().size();
        final var totalTransactions = transactions.size();
        var expenses = BigDecimal.valueOf(0);
        var incomes = BigDecimal.valueOf(0);

        for (final var transaction : transactions) {
            if (transaction.getCategory().getType().equals(CategoryType.EXPENSE)) {
                expenses = expenses.add(transaction.getValue());
            } else {
                incomes = incomes.add(transaction.getValue());
            }
        }

        final var totalExpenses = currencyConverter.toString(expenses);
        final var totalIncomes = currencyConverter.toString(incomes);

        return new MainStatsReportResponse(totalCategories, totalTransactions, totalExpenses, totalIncomes);
    }
}
