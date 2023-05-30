package com.abb.expensemanager.mapper;

import com.abb.expensemanager.model.dto.UsersCategoryRequest;
import com.abb.expensemanager.model.dto.UsersCategoryResponse;
import com.abb.expensemanager.model.entity.UsersCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * The users category mapper.
 */
@Mapper(componentModel = "spring")
public interface UsersCategoryMapper {

    @Mapping(target = "userId", source = "user.id")
    UsersCategoryResponse toResponse(UsersCategory entity);

    List<UsersCategoryResponse> toResponses(List<UsersCategory> entities);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "category.id", source = "categoryId")
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isEnabled", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    UsersCategory toEntity(UsersCategoryRequest request);

}
