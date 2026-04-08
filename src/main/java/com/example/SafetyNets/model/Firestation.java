package com.example.SafetyNets.model;

public class Firestation {
    private String address;
    private String station;

    //constructor: default and with parameter or with annotation @AutoWired( both can be left in this project because no need for JPA neither SQL nor deserialization for JSON)

    public Firestation(){};
    public Firestation(String address, String station) {
        this.address = address;
        this.station = station;
    }

    // getters & setters

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}
