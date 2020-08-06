package com.example.petracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonManager {

    ArrayList<User> usersFromApi = new ArrayList<>();
    ArrayList<Customer> customersFromApi = new ArrayList<>();
    ArrayList<Pet> petsFromApi = new ArrayList<>();
    ArrayList<Breed> breedsFromApi = new ArrayList<>();
    ArrayList<AdoptionCenter> centersFromApi = new ArrayList<>();
    ArrayList<Clinic> clinicsFromApi = new ArrayList<>();

    void parseUserData(String json){
        try{
            JSONArray array = new JSONArray(json);
            for(int i = 0; i < array.length(); i++){
                JSONObject userOBJ = array.getJSONObject(i);
                String id = userOBJ.getString("_id");
                String uname = userOBJ.getString("username");
                String psswd = userOBJ.getString("password");
                String cus_id = userOBJ.getString("customer");
                Customer customer = getCustomerData(cus_id);
                User user = new User(id,uname,psswd,customer);
                usersFromApi.add(user);
            }
        }catch(JSONException e) {
            e.printStackTrace();
        }
    }

//    void parseCustomerData(String json){
//        try{
//            JSONArray array = new JSONArray(json);
//            for(int i = 0; i < array.length(); i++){
//                JSONObject customerOBJ = array.getJSONObject(i);
//                String cName = customerOBJ.getString("cust_name");
//                String email = customerOBJ.getString("cust_email");
//                String id = customerOBJ.getString("_id");
//                String tracker = customerOBJ.getString("cust_tracker");
//                String phone = customerOBJ.getString("cust_phone");
//                // don't know yet how to reference array of pet ids
//
//            }
//        }catch (JSONException e){
//            e.printStackTrace();
//        }
//    }

    void parsePetData(String json){
        try{
            JSONArray array = new JSONArray(json);
            for(int i = 0; i < array.length(); i++){
                JSONObject petOBJ = array.getJSONObject(i);
                String id = petOBJ.getString("_id");
                String name = petOBJ.getString("pet_name");
                int age = petOBJ.getInt("pet_age");
                String breedID = petOBJ.getString("breed");
                Breed breed = getBreedData(breedID);
                Pet pet = new Pet(id,name,age,breed);
                petsFromApi.add(pet);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    void parseBreedData(String json){
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
    }

    void parseAdoptionCenterData(String json){
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
    }

    void parseClinicData(String json){
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
    }

    Customer getCustomerData(String id){
        Customer customer = new Customer();
        for(int i = 0; i < customersFromApi.size(); i++){
            if(id == customersFromApi.get(i).id){
                customer.id = customersFromApi.get(i).id;
                customer.customerName = customersFromApi.get(i).customerName;
                customer.email = customersFromApi.get(i).email;
                customer.phoneNumber = customersFromApi.get(i).phoneNumber;
                customer.tracker = customersFromApi.get(i).tracker;
                customer.pet = customersFromApi.get(i).pet;
            }
        }
        return customer;
    }

    Pet getPetData(String id){
        Pet pet = new Pet();
        for(int i = 0; i < petsFromApi.size(); i++){
            if(id == petsFromApi.get(i).id){
                pet.id = petsFromApi.get(i).id;
                pet.petName = petsFromApi.get(i).petName;
                pet.petAge = petsFromApi.get(i).petAge;
                pet.breed = petsFromApi.get(i).breed;
            }
        }
        return pet;
    }

    Breed getBreedData(String id){
        Breed breed = new Breed();

        for(int i = 0; i < breedsFromApi.size(); i++){
            if(id == breedsFromApi.get(i).id){
                breed.id = breedsFromApi.get(i).id;
                breed.breedType = breedsFromApi.get(i).breedType;
            }
        }
        return breed;
    }

}
