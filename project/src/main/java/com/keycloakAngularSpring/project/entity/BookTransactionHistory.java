package com.keycloakAngularSpring.project.entity;

import com.keycloakAngularSpring.project.entity.common.BaseEntity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

public class BookTransactionHistory extends BaseEntity {
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

    private boolean returned;
    private boolean returnApproved;
}
