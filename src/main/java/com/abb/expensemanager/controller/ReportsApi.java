package com.abb.expensemanager.controller;

import com.abb.expensemanager.model.dto.ReportMainDataResponse;
import com.abb.expensemanager.service.usecase.IReportsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The routes/endpoints for the user reports.
 */
@RestController
@RequestMapping(path = "/reports")
@RequiredArgsConstructor
public class ReportsApi {

    private final IReportsUseCase service;

    @GetMapping(path = "/{userId}")
    public ResponseEntity<ReportMainDataResponse> get(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(service.get(userId));
    }

}
