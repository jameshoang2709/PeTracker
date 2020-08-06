package com.example.petracker;

public class Pet {
    String id;
    String petName;
    int petAge;
    Breed breed;

    public Pet() {
    }

    public Pet(String id, String petName, int petAge, Breed breed) {
        this.id = id;
        this.petName = petName;
        this.petAge = petAge;
        this.breed = breed;
    }
}
