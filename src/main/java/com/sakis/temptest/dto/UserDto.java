package com.sakis.temptest.dto;

import com.sakis.temptest.annotation.ValidateUserGender;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

public class UserDto {

    @NotEmpty(message = "firstname must not be empty")
    private String firstName;
    @NotEmpty(message = "lastname must not be empty")
    private String lastName;
    @ValidateUserGender
    @NotEmpty(message = "gender must not be empty")
    private String gender;

    public UserDto() {
    }

    public UserDto(String firstName, String lastName, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
