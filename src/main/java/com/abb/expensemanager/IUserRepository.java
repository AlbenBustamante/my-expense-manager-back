package com.abb.expensemanager;

import com.abb.expensemanager.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The {@link User} crud repository.
 */
public interface IUserRepository extends JpaRepository<User, Integer> {

    /**
     * @param username the username to validate.
     * @return true if the user already exists.
     */
    boolean existsByUsername(String username);

    /**
     * @param email the email to validate.
     * @return true if the user already exists.
     */
    boolean existsByEmail(String email);

    /**
     * @param phone the phone to validate.
     * @return true if the user already exists.
     */
    boolean existsByPhone(String phone);

}
