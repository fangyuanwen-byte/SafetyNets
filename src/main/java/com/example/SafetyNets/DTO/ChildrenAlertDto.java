package com.example.SafetyNets.DTO;

import com.example.SafetyNets.model.Person;

import java.util.List;

public class ChildrenAlertDto {
    String firstName;
    String lastName;
    String age;
    List<Person> familyMembers;

    public ChildrenAlertDto(String firstName, String lastName, String age, List<Person> familyMembers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.familyMembers = familyMembers;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<Person> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(List<Person> familyMembers) {
        this.familyMembers = familyMembers;
    }
}
