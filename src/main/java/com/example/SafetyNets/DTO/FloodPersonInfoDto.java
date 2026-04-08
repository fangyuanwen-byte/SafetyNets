package com.example.SafetyNets.DTO;

import java.util.List;

public class FloodPersonInfoDto {
    String firstName;
    String lastName;
    String phone;
    int age;
    List<String> medications;
    List<String> allergies;

    public FloodPersonInfoDto(String firstName, List<String> allergies, List<String> medications, int age, String phone, String lastName) {
        this.firstName = firstName;
        this.allergies = allergies;
        this.medications = medications;
        this.age = age;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }
}
