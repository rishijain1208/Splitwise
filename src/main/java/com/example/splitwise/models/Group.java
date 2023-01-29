package com.example.splitwise.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Group {

    private String name;

    private List<User> participants;

    private List<User> admins;

    private String description;

    private User createdBy;

    private List<Expense> expenses;
}
