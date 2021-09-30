package com.example.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DoctolibCovidVaccines {

    @JsonProperty(value = "first_shot_ref_visit_motive_id")
    private String firstShotRefVisitMotiveId;

    @JsonProperty(value = "single_shot_ref_visit_motive_id")
    private String singleShotRefVisitMotiveId;

    public DoctolibCovidVaccines(String firstShotRefVisitMotiveId, String singleShotRefVisitMotiveId) {
        this.firstShotRefVisitMotiveId = firstShotRefVisitMotiveId;
        this.singleShotRefVisitMotiveId = singleShotRefVisitMotiveId;
    }

    public String getFirstShotRefVisitMotiveId() {
        return firstShotRefVisitMotiveId;
    }

    public String getSingleShotRefVisitMotiveId() {
        return singleShotRefVisitMotiveId;
    }
}
