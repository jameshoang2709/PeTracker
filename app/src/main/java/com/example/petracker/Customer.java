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
<<<<<<< HEAD
    ArrayList<String> petIds;
=======
    ArrayList<Pet> pet;
//    ArrayList<String> pet;

//    public void JsonM (String json) throws JSONException {
//        JSONObject cus = new JSONObject(json);
//        JSONArray JsonArrayPets = cus.getJSONArray("pets");
//        for (int i = 0; i < JsonArrayPets.length(); i++) this.pet.add(JsonArrayPets.getString(i));
//    }
>>>>>>> a8934a3b6fa1a1df5313a520d1a3633050e9d11d

    public Customer() {
    }

    public Customer(String id, String customerName, String email, String tracker, String phoneNumber, ArrayList<String> pet) {
        this.id = id;
        this.customerName = customerName;
        this.email = email;
        this.tracker = tracker;
        this.phoneNumber = phoneNumber;
        this.petIds = pet;
    }
}
