package com.abb.expensemanager.model.dto;

/**
 * The model dto for the user reports as response.
 */
public record ReportsResponse(MainStatsReportResponse mainStats, String currentBalance) {
}
