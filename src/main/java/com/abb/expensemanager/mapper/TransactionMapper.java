package com.abb.expensemanager.mapper;

import com.abb.expensemanager.model.dto.TransactionRegister;
import com.abb.expensemanager.model.dto.TransactionResponse;
import com.abb.expensemanager.model.entity.Transaction;
import com.abb.expensemanager.util.CurrencyConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The transaction mapper.
 */
@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "value", expression = "java(currencyConverter.toString(entity.getValue()))")
    TransactionResponse toResponse(Transaction entity, CurrencyConverter currencyConverter);

    default List<TransactionResponse> toResponses(List<Transaction> entities, CurrencyConverter currencyConverter) {
        if (entities == null) {
            return Collections.emptyList();
        }

        final List<TransactionResponse> list = new ArrayList<>(entities.size());

        for (final var transaction : entities) {
            list.add(toResponse(transaction, currencyConverter));
        }

        return list;
    }

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isEnabled", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "categoryId", ignore = true)
    @Mapping(target = "category", ignore = true)
    Transaction toEntity(TransactionRegister register);

}
