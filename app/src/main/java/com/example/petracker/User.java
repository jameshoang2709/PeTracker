package com.example.petracker;

public class User {
    String id;
    String Username;
    String Password;
    Customer customer;

    public User(String id, String username, String password, Customer customer) {
        this.id = id;
        Username = username;
        Password = password;
        this.customer = customer;
    }
}
