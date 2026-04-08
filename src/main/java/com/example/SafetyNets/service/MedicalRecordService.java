package com.example.SafetyNets.service;

import com.example.SafetyNets.model.MedicalRecord;
import com.example.SafetyNets.repository.DataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {

    private final DataRepository repo;

    public MedicalRecordService(DataRepository repo) {
        this.repo = repo;
    }

//Test: GetMapping for getAllMedicalRecords()
    public List<MedicalRecord> getAllMedicalRecords() {
        return repo.getMedicalRecords();
    }
//method to add a MedicalRecord
    public MedicalRecord add(MedicalRecord m) {
        repo.getMedicalRecords().add(m);
        repo.save();
        return m;
    }

//method to update a MedicalRecord
    public MedicalRecord update(MedicalRecord m) {
        List<MedicalRecord> list = repo.getMedicalRecords();

        for (int i = 0; i < list.size(); i++) {
            MedicalRecord existing = list.get(i);

            if (existing.getFirstName().equals(m.getFirstName()) &&
                    existing.getLastName().equals(m.getLastName())) {

                list.set(i, m);
                repo.save();
                return m;
            }
        }
        return null;
    }

//method to delete a MedicalRecord by searching firstName and lastName---------required for project
    public boolean delete(String first, String last) {
        boolean removed = repo.getMedicalRecords().removeIf(
                m -> m.getFirstName().equals(first) && m.getLastName().equals(last)
        );
        if (removed) repo.save();
        return removed;
    }


}