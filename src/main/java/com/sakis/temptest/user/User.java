package com.sakis.temptest.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User {

    private String id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Positive
    private int age;

    public User(String firstName, String lastName, int age) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
