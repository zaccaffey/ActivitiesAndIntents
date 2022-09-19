package com.example.activitiesandintents.roomdb;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "person")
public class Person {

    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    String email;
    String number;
    String pincode;
    String city;

    public Person(int id, String name, String email, String number, String pincode, String city) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.number = number;
        this.pincode = pincode;
        this.city = city;
    }

    @Ignore
    public Person(String name, String email, String number, String pincode, String city) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.pincode = pincode;
        this.city = city;
    }
}