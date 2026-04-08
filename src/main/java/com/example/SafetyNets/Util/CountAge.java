package com.example.SafetyNets.Util;

import com.example.SafetyNets.model.Firestation;
import com.example.SafetyNets.model.MedicalRecord;
import com.example.SafetyNets.model.Person;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class CountAge {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

   public static int CalculateAge(MedicalRecord mr){
        return Period.between( LocalDate.parse(mr.getBirthdate(),formatter),LocalDate.now()).getYears();
    }

   public static boolean IsAdult(MedicalRecord mr)
    {
        return Period.between( LocalDate.parse(mr.getBirthdate(),formatter), LocalDate.now()).getYears() > 18;
    }
}
