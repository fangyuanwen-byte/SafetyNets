package com.example.SafetyNets.DTO;

import java.util.List;

public class FloodResponseDto {

    List<FloodAddressInfoDto> households;

    public FloodResponseDto(List<FloodAddressInfoDto> households) {
        this.households = households;
    }

    public void setHouseholds(List<FloodAddressInfoDto> households) {
        this.households = households;
    }

    public List<FloodAddressInfoDto> getHouseholds() {
        return households;
    }
}
