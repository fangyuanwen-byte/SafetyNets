package com.example.SafetyNets.controller;

import com.example.SafetyNets.DTO.ChildrenAlertDto;
import com.example.SafetyNets.DTO.FireResponseDto;
import com.example.SafetyNets.DTO.FloodResponseDto;
import com.example.SafetyNets.DTO.PersonInfoDto;
import com.example.SafetyNets.model.Firestation;
import com.example.SafetyNets.service.AlertService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping()
//As required for this project, all URL start without a root path of AlertController as "alert", but directly with the root path of each Controller, so there is no need for @RequestMapping("alert")
//@RequestMapping("alert")

public class AlertController {

    private final AlertService service;

    public AlertController(AlertService service) {
        this.service = service;
    }
//for No.(1):method to getAllFirestations(): for No.(1)URL: /firestation?stationNumber=<number> eg.stationNumber=1
    //Done: get List<Person> then .getFirstName(); .getLastName(); .getPhone(); (.getAge();) .getAdultNum(); .getChildNum();----------without DTO, here return value is List<String>, all elements is String
    //TODO: use TDO to have a return value of List<PersonOfStation>, which is actually a multiply dimensional table
    @GetMapping("/alert/firestation")
    /// /V1: return List<String> for AlertService URLNo.1 V1 V2 V3
//    public List<String> getPersonsByStationV1(@RequestParam String stationNumber) {
//        return service.getPersonsByStation(stationNumber);
//    }

    ////V2: return Map<String,Object> with Dto
    public Map<String,Object> getPersonsByStation(@RequestParam String stationNumber){
        return service.getPersonsByStation(stationNumber);
    }


//Done: for No.(2) URL: /childAlert?address=<address> :method to getAllChildren by searching address :getFirstName(); getLastName(); getAge();getFamilyMembers(); return Null(if no child found)
    @GetMapping("/childAlert")
    //V1 : return value as Map<String,Object>
    public List<ChildrenAlertDto> childrenAlerts(String address){
        return service.childrenAlerts(address);
    }

//Done: for No.(3) URL : /phoneAlert?firestation=<number>
//for No.(3): method to getAllPhones(): for No.(3)URL: /phoneAlert?firestation=<number>
    @GetMapping("/phoneAlert")
    //V1: for way1 and way2 : return value of List<String>
    /*
    public List<String> getPhones(@RequestParam String firestation_number) {
        return service.getPhonesByStation(firestation_number);
    }

     */

    //V2: return value of Map<String, String>----------TODO: here, why not use Map<String, Object> but Map<String, String>?
    public Map<String,String> getPhones(@RequestParam String stationNumber){
        return service.getPhonesByStation(stationNumber);
    }
//

    @GetMapping("/fire")
//Done: for No.(4)URL /fire?address=<address>: method to :(i) get List<Person> by searching address ; person.firestName(); person.lastName; (ii) method to get List<MedicalRecords> ;then the method of person.getAge(String birthdate); then method of List<String> of medications; then method of List<String> of allergies;
    public FireResponseDto getFire(@RequestParam String address) {
        return service.getFireInfo(address);
    }

    @GetMapping("/flood/stations")
//Done: for No.(5) URL: /flood/stations?stations=<list>: (i) getAllFirestations() as a List<Firestation>; (ii) optional exo for me: List<Person> then create List<Family> by stream().filter( person.lastName);(iii) then with stream().filter(address) to .collect() person.firstName(); person.lastName(); (IV) method to get List<MedicalRecords> (V) method of getAge(medicalRecord.birthdate()); (VI) method of returning List<String> Medications ; method of returning List<String> Allergies;
    public FloodResponseDto getFlood(@RequestParam String stations){
        List<String> stationList = Arrays.asList(stations.split(","));
        return service.getFloodInfo(stationList);
    }

//Done: for No.(6) URL:  /personInfo?firstName=&lastName= (i) get List<Person> by searching firstName, lastName (ii) returning person.address; person.email; person.firstName; person.lastName;(iii)get List<MedicalRecord>, then method to getAge(medicalRecord.birthdate());method of returning two List<String> Medications + List<String> Allergies; or create a new List of this two List if it is possible as List<MedicationAllergies> ;(iv) use stream().filter and .collect()------(1)return List<String> without TDO;(2) another return List<PersonInfo> as multiple dimensional table with TDO
    @GetMapping("/personInfo")
    //V1: with return value of Map<String,Object>
    public List<PersonInfoDto> getPersonInfo(@RequestParam String firstName, @RequestParam String lastName){
        return service.getPersonInfo(firstName,lastName);

    }

//for No.(7): method to getAllEmails(): for No.(7)URL: /communityEmail?city=<city> eg.city=Culver---------done
    @GetMapping("/communityEmail")
    public List<String> getEmails(@RequestParam String city) {
        return service.getEmailsByCity(city);
    }

}