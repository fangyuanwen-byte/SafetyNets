package com.example.SafetyNets.model;

import java.util.List;

public class Data {

    /* attibutes: (1) by analyzing the structure of Data.json file, it contains a multiply-dimensional table which contains three List of Object.
                  (2) to access to the data information in Date.json as a file resource, we should create a class of DataHandler to load, read, write and save the data with Jackson dependency of SpringBoot web starter by using the I/O interface of File(URL).
                  (3) ObjectMapping: it is a versioned and serialized interface with method of mapper,firstly we should create the object of ObjectMapping, then use File to get the resource,once having the access of the Data.json, we use mapper method of readValue and writeValue to map the data information in data.json and the Data.class in Entity package.
                  (4) to be mapped as an entity which could be used after for the repository and be accessed by controller as well as by services as dataBase resources, Data.class should have the same type of object with data.json, which means here as three Lists of different objects.
                  (5) as entity, it should creat the Object then instantiate the objects (by constructor default normally) and should have the method of getter and setter to be served as the methods of Data object in DataRepository.

     */
    private List<Person> persons;
    private List<Firestation> firestations;
    private List<MedicalRecord> medicalrecords;

    //constructor: normally to inject JPA , Jackson or SQL, constructor default is necessary for every entity. but here in this project, there is no database nor Jackson reversed order, so constructor default can be generated automatically .
    public Data(){};
    public Data(List<Person> persons, List<Firestation> firestations, List<MedicalRecord> medicalrecords) {
        this.persons = persons;
        this.firestations = firestations;
        this.medicalrecords = medicalrecords;
    }

    // getters & setters

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Firestation> getFirestations() {
        return firestations;
    }

    public void setFirestations(List<Firestation> firestations) {
        this.firestations = firestations;
    }

    public List<MedicalRecord> getMedicalrecords() {
        return medicalrecords;
    }

    public void setMedicalrecords(List<MedicalRecord> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }
}
