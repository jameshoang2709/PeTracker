package com.example.petracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Customer {
    String id;
    String customerName;
    String email;
    String tracker;
    String phoneNumber;
    ArrayList<Pet> pet;
//    ArrayList<String> pet;

//    public void JsonM (String json) throws JSONException {
//        JSONObject cus = new JSONObject(json);
//        JSONArray JsonArrayPets = cus.getJSONArray("pets");
//        for (int i = 0; i < JsonArrayPets.length(); i++) this.pet.add(JsonArrayPets.getString(i));
//    }

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
