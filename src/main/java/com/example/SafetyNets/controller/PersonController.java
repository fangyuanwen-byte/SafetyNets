package com.example.SafetyNets.controller;

import com.example.SafetyNets.model.Person;
import com.example.SafetyNets.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }
    //TEST: getAllPerson
    @GetMapping("/")
    public List<Person> getPersons() {
        return service.getAll();
    }

    @PostMapping("/add")
    public Person add(@RequestBody Person p) {
        return service.add(p);
    }

    //update but do not change the firstName and LastName as required, so update by filtering the firstName and LastName
    @PutMapping("/update")
    public Person update(@RequestBody Person p) {
        return service.update(p);
    }

    @DeleteMapping("/delete")
    /* way1: delete by filtering firstName and LastName in parameter----required for this project
//    public void delete(@RequestParam String firstName, @RequestParam String lastName) {
//        service.delete(firstName, lastName);
//    }

     */

    /* way2: delete by filtering the email or phone number of the object in List---exo for me
//    public void  delete(@RequestParam String email,@RequestParam String phone) {
//        service.delete(email,phone);
//    }

     */

    //way3: delete by filtering the object in parameter and with the method of .remove() of List----exo for me
    public Person delete(@RequestParam Person p){
       return service.delete(p);
    }

}