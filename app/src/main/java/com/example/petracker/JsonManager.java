package com.example.petracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonManager {

    static ArrayList<User> parseUserData(String json){
        ArrayList<User> usersFromApi = new ArrayList<>();
        try{
            JSONArray array = new JSONArray(json);
            for(int i = 0; i < array.length(); i++){
                JSONObject userOBJ = array.getJSONObject(i);
                String id = userOBJ.getString("_id");
                String uname = userOBJ.getString("username");
                String psswd = userOBJ.getString("password");
                String cus_id = userOBJ.getString("customer");
                User user = new User(id,uname,psswd,cus_id);
                usersFromApi.add(user);
            }
        }catch(JSONException e) {
            e.printStackTrace();
        }

        return usersFromApi;
    }

    static ArrayList<Customer> parseCustomerData(String json){
        ArrayList<Customer> customersFromApi = new ArrayList<>();
        try{
            JSONArray array = new JSONArray(json);
            for(int i = 0; i < array.length(); i++){
                JSONObject customerOBJ = array.getJSONObject(i);
                String cName = customerOBJ.getString("cust_name");
                String email = customerOBJ.getString("cust_email");
                String id = customerOBJ.getString("_id");
                String tracker = customerOBJ.getString("cust_tracker");
                String phone = customerOBJ.getString("cust_phone");
                JSONArray idArray = customerOBJ.getJSONArray("pet");
                ArrayList<String> pets = new ArrayList<>();
                for (int j = 0; j < idArray.length(); j++){
                    pets.add(idArray.getString(i));
                }
                Customer customer = new Customer(id,cName,email,tracker,phone,pets);
                customersFromApi.add(customer);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        return customersFromApi;
    }

    ArrayList<Pet> parsePetData(String json){
        ArrayList<Pet> petsFromApi = new ArrayList<>();
        try{
            JSONArray array = new JSONArray(json);
            for(int i = 0; i < array.length(); i++){
                JSONObject petOBJ = array.getJSONObject(i);
                String id = petOBJ.getString("_id");
                String name = petOBJ.getString("pet_name");
                int age = petOBJ.getInt("pet_age");
                String breedID = petOBJ.getString("breed");
                Pet pet = new Pet(id,name,age,breedID);
                petsFromApi.add(pet);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        return petsFromApi;
    }

    ArrayList<Breed> parseBreedData(String json){
        ArrayList<Breed> breedsFromApi = new ArrayList<>();
        try{
            JSONArray array = new JSONArray(json);
            for(int i = 0; i < array.length(); i++){
                JSONObject breedOBJ = array.getJSONObject(i);
                String id = breedOBJ.getString("_id");
                String type = breedOBJ.getString("breed_type");
                Breed breed = new Breed(id,type);
                breedsFromApi.add(breed);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        return breedsFromApi;
    }

    ArrayList<AdoptionCenter> parseAdoptionCenterData(String json){
        ArrayList<AdoptionCenter> centersFromApi = new ArrayList<>();
        try{
            JSONArray array = new JSONArray(json);
            for(int i = 0; i < array.length(); i++){
                JSONObject centerOBJ = array.getJSONObject(i);
                String id = centerOBJ.getString("_id");
                String name = centerOBJ.getString("name");
                String addr = centerOBJ.getString("address");
                AdoptionCenter center = new AdoptionCenter(id,name,addr);
                centersFromApi.add(center);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return centersFromApi;
    }

    ArrayList<Clinic> parseClinicData(String json){
        ArrayList<Clinic> clinicsFromApi = new ArrayList<>();
        try{
            JSONArray array = new JSONArray(json);
            for(int i = 0; i < array.length(); i++){
                JSONObject clinicOBJ = array.getJSONObject(i);
                String id = clinicOBJ.getString("_id");
                String name = clinicOBJ.getString("name");
                String addr = clinicOBJ.getString("address");
                Clinic clinic = new Clinic(id,name,addr);
                clinicsFromApi.add(clinic);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return clinicsFromApi;
    }
}
