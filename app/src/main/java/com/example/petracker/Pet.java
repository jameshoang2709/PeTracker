package com.example.petracker;

public class Pet {
    String id;
    String petName;
    int petAge;
    String breedId;

    public Pet() {
    }

    public Pet(String id, String petName, int petAge, String breed) {
        this.id = id;
        this.petName = petName;
        this.petAge = petAge;
        this.breedId = breed;
    }
}
