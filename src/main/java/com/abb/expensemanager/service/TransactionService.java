package com.abb.expensemanager.service;

import com.abb.expensemanager.exception.AppException;
import com.abb.expensemanager.mapper.TransactionMapper;
import com.abb.expensemanager.mapper.UsersCategoryMapper;
import com.abb.expensemanager.model.dto.TransactionRegister;
import com.abb.expensemanager.model.dto.TransactionResponse;
import com.abb.expensemanager.model.dto.UsersCategoryRequest;
import com.abb.expensemanager.model.entity.Category;
import com.abb.expensemanager.repository.ICategoryRepository;
import com.abb.expensemanager.repository.ITransactionRepository;
import com.abb.expensemanager.repository.IUserRepository;
import com.abb.expensemanager.repository.IUsersCategoryRepository;
import com.abb.expensemanager.service.usecase.ITransactionUseCase;
import com.abb.expensemanager.util.CurrencyConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The transactions service.
 */
@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionUseCase {

    private final TransactionMapper mapper;
    private final ITransactionRepository repository;
    private final ICategoryRepository categoryRepository;
    private final UsersCategoryMapper usersCategoryMapper;
    private final IUsersCategoryRepository usersCategoryRepository;
    private final IUserRepository userRepository;
    private final CurrencyConverter currencyConverter;

    @Override
    public TransactionResponse create(TransactionRegister register) {
        if (!userRepository.existsById(register.userId())) {
            throw new AppException("The user don't exists.", HttpStatus.NOT_FOUND);
        }

        final var categoryFound = categoryRepository.findByName(register.category().name());
        var newCategory = new Category();

        if (categoryFound.isEmpty()) {
            newCategory.setName(register.category().name());
            newCategory.setType(register.category().type());
            newCategory = categoryRepository.save(newCategory);

            final var usersCategory = usersCategoryMapper.toEntity(new UsersCategoryRequest(register.userId(), register.category()));
            usersCategory.getId().setCategoryId(newCategory.getId());

            usersCategoryRepository.save(usersCategory);
        }

        final var entity = mapper.toEntity(register);

        entity.setCategoryId(categoryFound.isEmpty() ? newCategory.getId() : categoryFound.get().getId());

        return mapper.toResponse(repository.save(entity), currencyConverter);
    }

    @Override
    public Optional<List<TransactionResponse>> getAllByUser(int userId) {
        return repository.findAllByUserId(userId).map(transactions -> mapper.toResponses(transactions, currencyConverter));
    }

}
