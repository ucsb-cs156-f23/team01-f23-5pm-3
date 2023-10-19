package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ucsb.cs156.spring.backenddemo.services.PublicHolidayQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name="Public holidays info from https://date.nager.at/api/v2/publicholidays/{year}/{countryCode}")
@Slf4j
@RestController
@RequestMapping("/api/publicHolidays")
public class PublicHolidaysController {
        ObjectMapper mapper = new ObjectMapper();

    @Autowired
    PublicHolidayQueryService publicHolidayQueryService;

    @Operation(summary = "Get public holidays given the year and country code", description = "JSON return format documented here: https://date.nager.at/api")
    @GetMapping("/get")
    public ResponseEntity<String> getPublicHolidays(
        @Parameter(name="year", description="calendar year", example="2023") @RequestParam String year,
        @Parameter(name="countryCode", description="ISO two-letter country code", example="AT") @RequestParam String countryCode
    ) throws JsonProcessingException {
        log.info("getPublicHolidays: year={} countryCode={}", year, countryCode);
        String result = publicHolidayQueryService.getJSON(year, countryCode);
        return ResponseEntity.ok().body(result);
    }
}
