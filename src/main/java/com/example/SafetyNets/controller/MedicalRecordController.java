package com.example.SafetyNets.controller;


import com.example.SafetyNets.model.MedicalRecord;
import com.example.SafetyNets.service.MedicalRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    private final MedicalRecordService service;

    public MedicalRecordController(MedicalRecordService service) {
        this.service = service;
    }

//Test: GetMapping for getAllMedicalRecords()
    @GetMapping("/")
    public List<MedicalRecord> getAllMedicalRecords(){
        return service.getAllMedicalRecords();
    }

//method to add a MedicalRecord
    @PostMapping("/add")
    public MedicalRecord add(@RequestBody MedicalRecord m) {
        return service.add(m);
    }

//method to update a MedicalRecord
    @PutMapping("/update")
    public MedicalRecord update(@RequestBody MedicalRecord m) {
        return service.update(m);
    }

//method to delete a MedicalRecord by searching firstName and lastName---------required for project
    @DeleteMapping("/delete")
    public void delete(@RequestParam String firstName, @RequestParam String lastName) {
        service.delete(firstName, lastName);
    }

}