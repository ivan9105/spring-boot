package com.domclick.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "BANK_ACCOUNT")
public class Account extends BaseEntity {
    @NotNull(message = "Баланс не может быть пустым")
    @PositiveOrZero(message = "Баланс не может быть отрицательным")
    @Column(name = "BALANCE", nullable = false)
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Transient
    private Long userId;

    @Transient
    private String userStr;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserStr() {
        return user.toString();
    }

    public void setUserStr(String userStr) {
        this.userStr = userStr;
    }

    public void updateUserId() {
        this.userId = user != null ? user.getId() : null;
    }
}
