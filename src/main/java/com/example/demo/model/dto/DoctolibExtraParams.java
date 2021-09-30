package com.example.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DoctolibExtraParams {

    @JsonProperty(value = "extra_paramsref_visit_motive_ids[]")
    private String extraParamsVisitMotiveId;

    public DoctolibExtraParams(String extraParamsVisitMotiveId) {
        this.extraParamsVisitMotiveId = extraParamsVisitMotiveId;
    }
}
