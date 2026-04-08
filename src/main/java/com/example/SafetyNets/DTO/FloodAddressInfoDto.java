package com.example.SafetyNets.DTO;

import java.util.List;

public class FloodAddressInfoDto {
    String address;
    List<FloodPersonInfoDto> persons;

    public FloodAddressInfoDto(String address, List<FloodPersonInfoDto> persons) {
        this.address = address;
        this.persons = persons;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<FloodPersonInfoDto> getPersons() {
        return persons;
    }

    public void setPersons(List<FloodPersonInfoDto> persons) {
        this.persons = persons;
    }
}
