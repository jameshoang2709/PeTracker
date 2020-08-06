package com.example.petracker;

public class User {
    String id;
    String Username;
    String Password;
    String customerId;

    public User(String id, String username, String password, String customer) {
        this.id = id;
        Username = username;
        Password = password;
        this.customerId = customer;
    }
}
