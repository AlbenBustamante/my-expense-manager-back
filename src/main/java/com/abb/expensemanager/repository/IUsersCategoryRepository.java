package com.abb.expensemanager.repository;

import com.abb.expensemanager.model.entity.UsersCategory;
import com.abb.expensemanager.model.entity.UsersCategoryPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The {@link UsersCategory} crud repository.
 */
public interface IUsersCategoryRepository extends JpaRepository<UsersCategory, UsersCategoryPK> {
}
