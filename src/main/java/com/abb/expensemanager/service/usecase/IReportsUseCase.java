package com.abb.expensemanager.service.usecase;

import com.abb.expensemanager.model.dto.ReportsResponse;

/**
 * The uses case for the reports.
 */
public interface IReportsUseCase {

    /**
     * Generate and get the reports for the user.
     *
     * @param userId the user id to search.
     * @return the report's response.
     */
    ReportsResponse get(int userId);

}
