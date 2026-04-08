package com.example.SafetyNets.service;

import com.example.SafetyNets.model.Firestation;
import com.example.SafetyNets.repository.DataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirestationService {

    private final DataRepository repo;

    public FirestationService(DataRepository repo) {
        this.repo = repo;
    }
//Test with the method of getFirestation
    public List<Firestation> getAllStations() {
        return repo.getFirestations();
    }

//method to add new firestation by add a new address
    /* way1: add a new object without verifying the condition for his two attributes
//    public Firestation add(Firestation f) {
//        repo.getFirestations().add(f);
//        repo.save();
//        return f;
//    }

     */

    //way2: add a new object of station by verifying the condition , if any of its attributes is already exist, should re-assign it to an existing station, if not, create a new one
    public Firestation add(Firestation f){
        //verifying if the address or the station number is already exist.Because these two attributes are Many to Many related, since any of them do not exist , we should create a new station. if not,do not add.
        for(Firestation fs: repo.getFirestations()){
            if(!fs.getAddress().equals(f.getAddress())||!fs.getStation().equals(f.getStation())){
                add(f);
                return f;
            }
        }
        return null;
    }

    //method to update
    // method to update: 1 station number by assigning the new address for the firestation
    public Firestation update(Firestation f) {
//1: create List of Firestation from JSON file
        List<Firestation> list = repo.getFirestations();

//2: update the address of firestation-
    // method to update: 1----required for the project
        //way1: enhanced for
        for(Firestation fs : list){
            if(fs.getAddress().equals(f.getAddress())){
                fs.setStation(f.getAddress());
                repo.save();
                return fs;
            }else {
                System.out.println(f.getAddress() + " not found");
                return null;
            }
        }

        /* way2: for classic
        for (int i = 0; i < list.size(); i++) {
            Firestation existing = list.get(i);

            if (existing.getAddress().equals(f.getAddress())) {
                list.set(i, f);
                repo.save();
                return f;
            }
        }

         */

//3: if the address do not exist in the List<Firestation>, return null
        return null;
        }

        /* method to update: 2 method to update the station address of an existing station number----exo for me
    public Firestation update(String station,String newAddress) {
        List<Firestation> fs = repo.getFirestations();
        for(Firestation f :fs){
            if(f.getStation().equals(station)){
                f.setAddress(newAddress);
                repo.save();
                return f;
            }else {
                System.out.println(station+" not found");
            }
        }
        return null;
    }
         */

        /* method to update: 3 method to update the station address as well as the station number by it ancient station number----exo for me
    public Firestation update(String oldStation, String oldAddress, String newAddress, String newStation) {
        List<Firestation> fs = repo.getFirestations();
        for(Firestation f: fs){
        if(f.getStation().equals(oldStation)|| f.getAddress().equals(oldAddress)){
            f.setAddress(newAddress);
            f.setStation(newStation);
            repo.save();
            return f;
        }else {
            System.out.println("Station Not Found");
        }
        }
        return  null;
    }
         */

//method to delete
    //way1: delete by searching the address by using the method of Collection or List
        //first way: use method .removeIf() of Collection
//    public boolean delete(String address) {
//        boolean removed = repo.getFirestations().removeIf(
//                f -> f.getAddress().equals(address)
//        );
//        if (removed) repo.save();
//        return removed;
//    }
        //second way: use the method .remove of List with enhanced for loop or classic for loop
      public void delete(String station){
        List<Firestation> list = repo.getFirestations();
            //(1) enhanced for loop
        for(Firestation fs: list){
          if(fs.getStation().equals(station)){
              list.remove(fs);
            }
        }
            /* (2)classic for loop
        for(int i=0;i<list.size();i++){
            if(list.get(i).getStation().equals(station)){
                list.remove(list.get(i));
            }
        }

             */
        repo.save();
    }



    //TODO: way2: delete by searching the address by using the method of Map



}