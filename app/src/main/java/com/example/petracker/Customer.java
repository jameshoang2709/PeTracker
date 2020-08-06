package com.example.petracker;

import java.util.ArrayList;

public class Customer {
    String id;
    String customerName;
    String email;
    String tracker;
    String phoneNumber;
    ArrayList<Pet> pet;

    public Customer() {
    }

    public Customer(String id, String customerName, String email, String tracker, String phoneNumber, ArrayList<Pet> pet) {
        this.id = id;
        this.customerName = customerName;
        this.email = email;
        this.tracker = tracker;
        this.phoneNumber = phoneNumber;
        this.pet = pet;
    }
}
