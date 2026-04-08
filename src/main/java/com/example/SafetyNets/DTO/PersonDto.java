package com.example.SafetyNets.DTO;

public class PersonDto {
    //attributes
    String firstName;
    String lastName;
    String address;
    String phoneNumber;


    //constructor
    public PersonDto(){}

    public PersonDto(String firstName, String lastName, String adress, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = adress;
        this.phoneNumber = phoneNumber;
    }

    //getter and setter
    public String getFirstName(){
        return firstName;
    };
    public String getLastName(){
        return  lastName;
    }
    public String getAdress(){
        return address;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setFirstName(String firstName){
        this.firstName= firstName;
    }
    public void setLastName(String lastName){
        this.lastName= lastName;
    }
    public void setAdress(String adress){
        this.address = adress;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber= phoneNumber;
    }

}
