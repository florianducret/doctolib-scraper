package com.example.demo.api;

import com.example.demo.service.ScraperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/searchVaccinationSchedule")
public class SearchVaccinationScheduleController {

    private final ScraperService scraperService;

    public SearchVaccinationScheduleController(ScraperService scraperService) {
        this.scraperService = scraperService;
    }

    @GetMapping
    public ResponseEntity getVaccinationSchedule() {
        scraperService.getVaccinationCenterId();
        return ResponseEntity.ok().build();
    }

}
