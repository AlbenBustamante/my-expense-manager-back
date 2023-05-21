package com.abb.expensemanager.mapper;

import com.abb.expensemanager.model.dto.UserRegister;
import com.abb.expensemanager.model.dto.UserResponse;
import com.abb.expensemanager.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * The user's mapper.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(User entity);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "isEnabled", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User toEntity(UserRegister register);

}
