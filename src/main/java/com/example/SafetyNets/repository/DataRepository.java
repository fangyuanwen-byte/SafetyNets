package com.example.SafetyNets.repository;

import com.example.SafetyNets.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataRepository {

    private final DataHandler handler;

    public DataRepository(DataHandler handler) {
        this.handler = handler;
    }

    public List<Person> getPersons() {
        //the method handler.getData() is written in class of DataHandler which loads the file of Data.json and contains the method of getData() and SaveData() of the object of Data, so that we can call the method of getter and setter in Data.class as a model.
        return handler.getData().getPersons();
    }

    public List<Firestation> getFirestations() {
        return handler.getData().getFirestations();
    }

    public List<MedicalRecord> getMedicalRecords() {
        return handler.getData().getMedicalrecords();
    }

    public void save() {
        handler.saveData();
    }
}