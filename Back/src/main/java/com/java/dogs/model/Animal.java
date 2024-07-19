package com.java.dogs.model;

import java.util.Date;
import java.time.Period;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String image;
    private String description;
    private String category;
    private Date dateOfBirth;
    private boolean status;

    public Animal(String name, String image, String description, String category,
        Date dateOfBirth, boolean status) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.category = category;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
    }

    public Animal() {
        this.name = " ";
        this.image = " ";
        this.description = " ";
        this.category = " ";
        this.dateOfBirth = new Date();
        this.status = false;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getImage() {return image;}
    public void setImage(String image) {this.image = image;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}

    public String getDateOfBirth() {return dateOfBirth.toString();}

    public boolean getStatus() {return status;}
    public void setStatus(boolean status) {this.status = status;}

    public long getAge() {return (new Date().getTime() - dateOfBirth.getTime()) /
        (1000L * 60 * 60 * 24 * 365);}
    
}