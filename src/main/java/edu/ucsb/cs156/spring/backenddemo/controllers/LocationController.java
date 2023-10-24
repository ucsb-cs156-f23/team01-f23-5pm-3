package edu.ucsb.cs156.spring.backenddemo.controllers;


import edu.ucsb.cs156.spring.backenddemo.services.CountryCodeQueryService;
import edu.ucsb.cs156.spring.backenddemo.services.LocationQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RestController;

@Tag(name="location info from https://public.opendatasoft.com/explore/dataset/countries-codes")
@Slf4j
@RequestMapping("/api/locations")
@RestController

public class LocationController {

     ObjectMapper mapper = new ObjectMapper();

    @Autowired
    LocationQueryService locationQueryService;
    
    @Operation(summary="Get your location and more", description ="Location data uploaded to OpenDataSoft by the International Labour Organization")
    @GetMapping("/get")
    public ResponseEntity<String> getLocation(
        @Parameter(name="location", example="United States") @RequestParam String location
    ) throws JsonProcessingException {
        log.info("getLocation: location={}", location);
        String result = locationQueryService.getJSON(location);
        return ResponseEntity.ok().body(result);
    }
}
