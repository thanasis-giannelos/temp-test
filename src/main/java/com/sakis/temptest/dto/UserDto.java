package com.sakis.temptest.dto;

import jakarta.validation.constraints.NotEmpty;

public class UserDto {

    @NotEmpty(message = "firstname must not be empty")
    private String firstName;
    @NotEmpty(message = "lastname must not be empty")
    private String lastName;

    public UserDto() {
    }

    public UserDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return "UserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
