package com.abb.expensemanager.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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

}
