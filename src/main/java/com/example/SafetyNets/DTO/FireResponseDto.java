package com.example.SafetyNets.DTO;

import java.util.List;

public class FireResponseDto {
   String stationNumber;
   List<FirePersonInfoDto> personInfoDtos;

    public FireResponseDto(String stationNumber, List<FirePersonInfoDto> personInfoDtos) {
        this.stationNumber = stationNumber;
        this.personInfoDtos = personInfoDtos;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
    }

    public List<FirePersonInfoDto> getPersonInfoDtos() {
        return personInfoDtos;
    }

    public void setPersonInfoDtos(List<FirePersonInfoDto> personInfoDtos) {
        this.personInfoDtos = personInfoDtos;
    }
}
