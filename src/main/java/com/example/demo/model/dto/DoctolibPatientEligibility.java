package com.example.demo.model.dto;

import java.util.ArrayList;
import java.util.List;

public class DoctolibPatientEligibility {

    private String id;

    private List<DoctolibCovidVaccines> covidVaccines;

    private DoctolibSpeciality speciality;

    public DoctolibPatientEligibility(String id, List<DoctolibCovidVaccines> covidVaccines, DoctolibSpeciality speciality) {
        this.id = id;
        this.covidVaccines = covidVaccines;
        this.speciality = speciality;
    }

    public String getId() {
        return id;
    }

    public List<DoctolibCovidVaccines> getCovidVaccines() {
        return covidVaccines == null ? new ArrayList<>() : covidVaccines;
    }
}
