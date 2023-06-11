package com.abb.expensemanager.model.dto;

/**
 * The response data for the main reports on the user dashboard.
 */
public record ReportMainDataResponse(int totalCategories, int totalTransactions, String totalExpenses,
                                     String totalIncomes) {
}
