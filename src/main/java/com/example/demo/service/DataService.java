package com.example.demo.service;

import com.example.demo.model.dto.DoctolibPatientEligibility;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataService {

    public List<String> getIds(DoctolibPatientEligibility doctolibPatientEligibility) {
        List<String> result = new ArrayList<>();
        doctolibPatientEligibility.getCovidVaccines().stream().forEach(cv -> {
            result.add(cv.getFirstShotRefVisitMotiveId());
            result.add(cv.getSingleShotRefVisitMotiveId());
        });
        return result;
    }

}
