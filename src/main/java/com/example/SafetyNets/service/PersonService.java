package com.example.SafetyNets.service;


import com.example.SafetyNets.model.Person;
import com.example.SafetyNets.repository.DataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final DataRepository repo;

    public PersonService(DataRepository repo) {
        this.repo = repo;
    }

    //Test: method to get all person as
    public List<Person> getAll() {
        return repo.getPersons();
    }

    //here, because we would like to add an object of Person, so we need to create an object of Person
    public Person add(Person p) {
        //add a person into the List<Person> by entering the value in requestBody in postman then save it. (.add() and .save() are the methods of List)
        repo.getPersons().add(p);
        repo.save();
        return p;
    }

    //method to update by FirstName and LastName
    public Person update(Person p) {
//1: create a List of object Person by getting the return value of DataRepository
        List<Person> persons = repo.getPersons();
//2: write a method for irritating  all the object in the List so that can filter the result by setting the  conditions, then return to the object has been updated.
        //way1: for classic
//        for (int i = 0; i < persons.size(); i++) {
//            Person existing = persons.get(i);
//
//            if (existing.getFirstName().equals(p.getFirstName()) &&
//                    existing.getLastName().equals(p.getLastName())) {
//                //way 1: to update the object of List at once by using the method .set(index, object) of List
//              persons.set(i, p);
//                /* way 2: to update any object corresponding to the condition and reset any attribute of the object one after another by using the method of List
//                persons.get(i).setFirstName(p.getFirstName());
//                persons.get(i).setLastName(p.getLastName());
//                persons.get(i).setAddress(p.getAddress());
//                persons.get(i).setCity(p.getCity());
//                persons.get(i).setZip(p.getZip());
//                persons.get(i).setPhone(p.getPhone());
//                persons.get(i).setEmail(p.getEmail());
//
//                 */
//                //save
//                repo.save();
//                return p;
//            }
//        }
        //way2: enhanced for
        for (Person person : persons) {
            if (person.getFirstName().equals(p.getFirstName()) && person.getLastName().equals(p.getLastName())) {
                persons.set(persons.indexOf(person), p);
                repo.save();
            }
        }
//3: as required, if the object to be updated is not exist in the JSON file list, return null.
        return null;
    }
    //method to delete a person by filtering firstName and LastName
    /* way1: uss the method .removeIf() of Collection because List<> belongs to java.util.Collection
    public boolean delete(String first, String last) {

        boolean removed = repo.getPersons().removeIf(
                p -> p.getFirstName().equals(first) && p.getLastName().equals(last)
        );
        if (removed) repo.save();
        //according to the method .removeIf(), the return value will be true if removed and false if it is not removed.
        return removed;
    }
    */

    //way2: method with no return value for delete and use the method remove of List but the method of .removeIf() of Collection
//    public void delete(String email, String phone) {
//        for  (Person person : repo.getPersons()) {
//            if (person.getEmail().equals(email) && person.getPhone().equals(phone)) {
//                repo.getPersons().remove(person);
//            }else{
//                System.out.println("Not found");
//            }
//        }
//    }

    //way3: method with return of object Person in parameter and the method of .contains() and .remove() of List to verify the condition and to delete the object
    public Person delete(Person p) {
        if(repo.getPersons().contains(p)) {
            repo.getPersons().remove(p);
            repo.save();
            return p;
        }else  {
            System.out.println("No such person");
            return null;
        }
    }


}