package com.abb.expensemanager.service.usecase;

import com.abb.expensemanager.model.dto.ReportMainDataResponse;

/**
 * The uses case for the reports.
 */
public interface IReportsUseCase {

    /**
     * Get the main data for the user reports.
     *
     * @param userId the user id to search.
     * @return the main data response.
     */
    ReportMainDataResponse get(int userId);

}
