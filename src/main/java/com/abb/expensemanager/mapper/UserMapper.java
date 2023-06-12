package com.abb.expensemanager.mapper;

import com.abb.expensemanager.model.dto.UserRegister;
import com.abb.expensemanager.model.dto.UserResponse;
import com.abb.expensemanager.model.entity.User;
import com.abb.expensemanager.util.constants.DateFormats;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * The user's mapper.
 */
@Mapper(componentModel = "spring", uses = UsersCategoryMapper.class)
public interface UserMapper {

    UserResponse toResponse(User entity);

    @Mapping(target = "birthday", dateFormat = DateFormats.DATE_FORMAT)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "isEnabled", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "transactions", ignore = true)
    User toEntity(UserRegister register);

}
