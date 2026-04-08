package com.example.SafetyNets.controller;

import com.example.SafetyNets.model.Firestation;
import com.example.SafetyNets.service.FirestationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/firestation")
public class FirestationController {

    private final FirestationService service;

    public FirestationController(FirestationService service) {
        this.service = service;
    }

//Test with the method of getFirestation
    @GetMapping("/")
    public List<Firestation> getAllStations(){
        return service.getAllStations();
    }
//method to add new address for firestation
    @PostMapping("/add")

    /* way1: add a new object without verifying the condition for his two attributes

//    public Firestation add(@RequestBody Firestation f) {
//        return service.add(f);
//    }

     */
    //way2: add a new object of station by verifying the condition , if any of its attributes are already exist, should re-assign it to an existing station, if not, create a new one
    public Firestation add(@RequestBody Firestation f){
        return service.add(f);
    }


//method to update: 1 update the station number of an address exiting in the List------required for the project
    @PutMapping("/update")
    public Firestation update(@RequestBody Firestation f) {
        return service.update(f);
    }

    /* method to update: 2 method to update the station address of an existing number----exo for me
    public Firestation update(@RequestParam String station,@RequestParam String newAddress) {
        return service.update(station,newAddress);
    }

     */

    /* method to update: 3 method to update the station address as well as the station number by it ancient station number----exo for me
//    public Firestation update(@RequestParam String oldStation, @RequestParam String newStation,@RequestParam String oldAddress, @RequestParam String newAddress) {
//        return service.update(oldStation,oldAddress,newAddress,newStation);
//    }

     */

//method to delete
    //way1: delete by searching the address
    @DeleteMapping("/delete")
    public void delete(@RequestParam String address) {
        service.delete(address);
    }
}