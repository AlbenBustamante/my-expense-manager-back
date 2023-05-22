package com.abb.expensemanager.repository;

import com.abb.expensemanager.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

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

    /**
     * Search a user by the username, email or phone.
     *
     * @param username the username to search.
     * @param email    the email to search.
     * @param phone    the phone to search.
     * @return an optional of the user found.
     */
    Optional<User> findByUsernameOrEmailOrPhone(String username, String email, String phone);

}
