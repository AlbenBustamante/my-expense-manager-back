package com.abb.expensemanager.repository;

import com.abb.expensemanager.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The {@link Category} crud repository.
 */
public interface ICategoryRepository extends JpaRepository<Category, Integer> {

    /**
     * Search an existing category by the name.
     *
     * @param name the name to search.
     * @return an optional of the category found.
     */
    Optional<Category> findByName(String name);

}
