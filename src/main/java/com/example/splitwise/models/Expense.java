package com.example.splitwise.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Expense extends BaseModel{

    // U E
    // 1 m
    // 1 1
    private double amount;

    private String Description;

    //@ManyToOne
    private User createdBy;

    //@ManyToOne
    private Currency baseCurrency;

    //@ManyToMany
    private List<User> participants;
}
