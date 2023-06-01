package com.abb.expensemanager.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * The users categories PK model entity.
 */
@Embeddable
@Getter
@Setter
public class UsersCategoryPK implements Serializable {

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "category_id")
    private Integer categoryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersCategoryPK that = (UsersCategoryPK) o;
        return Objects.equals(userId, that.userId) && Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, categoryId);
    }
    
}
