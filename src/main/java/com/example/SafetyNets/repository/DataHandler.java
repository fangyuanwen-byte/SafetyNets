package com.example.SafetyNets.repository;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.SafetyNets.model.Data;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.io.File;

@Component
public class DataHandler {

    private Data data;

    @PostConstruct
    //method to load the Json file with ObjectMapper which is from package com.fasterxml.jackson.core, with the dependency of jackson which has been written in web-starter
    public void loadJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            //with the java.io which belongs to  I/O and use the interface of serializable
            File file = new File("src/main/resources/data.json");
            data = mapper.readValue(file, Data.class);
        } catch (Exception e) {
            throw new RuntimeException("Error loading JSON file", e);
        }
    }
    //method to get the data after loading the file JSON
    public Data getData() {
        return data;
    }
    //method to save the data
    public void saveData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("src/main/resources/data.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, data);
        } catch (Exception e) {
            throw new RuntimeException("Error saving JSON file", e);
        }
    }
}