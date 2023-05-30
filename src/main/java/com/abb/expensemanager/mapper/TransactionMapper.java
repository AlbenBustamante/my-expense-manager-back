package com.abb.expensemanager.mapper;

import com.abb.expensemanager.model.dto.TransactionRegister;
import com.abb.expensemanager.model.dto.TransactionResponse;
import com.abb.expensemanager.model.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * The transaction mapper.
 */
@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionResponse toResponse(Transaction entity);

    List<TransactionResponse> toResponses(List<Transaction> entities);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isEnabled", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "categoryId", ignore = true)
    @Mapping(target = "category", ignore = true)
    Transaction toEntity(TransactionRegister register);

}
