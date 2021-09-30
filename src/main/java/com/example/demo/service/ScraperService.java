package com.example.demo.service;

import com.example.demo.model.dto.DoctoLibAvailabilities;
import com.example.demo.model.dto.DoctolibPatientEligibility;
import com.example.demo.model.dto.DoctotlibCity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ScraperService {

    private final String url;

    private final ObjectMapper mapper;

    private final Client client;

    public ScraperService(@Value("${scraping.url}") String url, ObjectMapper mapper) {
        this.url = url;
        this.mapper = mapper;
        this.client = ClientBuilder.newClient();
    }

    public String get(String city) {
        DoctotlibCity doctotlibCity = searchCity(city);
        List<String> patientEligibilityShotIds = getPatientEligibilityShotIds();
        List<String> vaccinationCenterId = getVaccinationCenterId(doctotlibCity.getCity(), patientEligibilityShotIds);
        return "toto";
    }

    private DoctotlibCity searchCity(String city) {
        List<DoctotlibCity> doctotlibCities = client.target(url + "/patient_app/place_autocomplete.json")
                .queryParam("query", city)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get()
                .readEntity(new GenericType<List<DoctotlibCity>>() {
                });
        return doctotlibCities.get(0);
    }

    public List<String> getPatientEligibilityShotIds() {
        List<DoctolibPatientEligibility> doctolibPatientEligibilities = client.target(url + "/patient_eligibility_categories")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get()
                .readEntity(new GenericType<List<DoctolibPatientEligibility>>() {
                });

        DoctolibPatientEligibility doctolibPatientEligibility = doctolibPatientEligibilities.stream().filter(dpe -> "375".equalsIgnoreCase(dpe.getId())).findFirst().orElseThrow();
        return getShotIds(doctolibPatientEligibility);
    }

    public List<String> getVaccinationCenterId(String city, List<String> shotIds) {

        Map<String, String> data = new HashMap<>();
        String queryName = "ref_visit_motive_ids[]";
        shotIds.forEach(shotId -> {
            data.put(queryName, shotId);
        });
        try {
            Element element = Jsoup.connect(url + "/" + city).ignoreContentType(true).data(data).get();
            Elements elementsByClass = element.getElementsByClass("dl-search-result");
            List<String> result = new ArrayList<>();
            elementsByClass.stream().forEach(el -> {
                result.add(el.attr("id").split("-")[2]);
            });
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private List<DoctoLibAvailabilities> getAvailabilities(List<String> vaccinationCenterIds, List<String> shotIds) {
        Map<String, String> data = new HashMap<>();
        String queryName = "ref_visit_motive_ids";
        shotIds.forEach(shotId -> {
            data.put(queryName, shotId);
        });
        data.put("search_result_format","json.json");

        List<DoctotlibCity> doctotlibCities = client.target(url + "/search_results/" + vaccinationCenterIds)
                .queryParam()
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get()
                .readEntity(new GenericType<List<DoctotlibCity>>() {
                });
    }

    private List<String> getShotIds(DoctolibPatientEligibility doctolibPatientEligibility) {
        List<String> result = new ArrayList<>();
        doctolibPatientEligibility.getCovidVaccines().stream().forEach(cv -> {
            result.add(cv.getFirstShotRefVisitMotiveId());
            result.add(cv.getSingleShotRefVisitMotiveId());
        });
        return result;
    }
}
