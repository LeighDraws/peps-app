package com.project.peps.country.controller;

import com.project.peps.country.dto.CountryDTO;
import com.project.peps.country.dto.CountryRequest;
import com.project.peps.country.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public ResponseEntity<List<CountryDTO>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> getCountryById(@PathVariable Long id) {
        return ResponseEntity.ok(countryService.getCountryById(id));
    }

    @PostMapping
    public ResponseEntity<CountryDTO> createCountry(@Valid @RequestBody CountryRequest countryRequest) {
        return new ResponseEntity<>(countryService.createCountry(countryRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryDTO> updateCountry(@PathVariable Long id, @Valid @RequestBody CountryRequest countryRequest) {
        return ResponseEntity.ok(countryService.updateCountry(id, countryRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return ResponseEntity.noContent().build();
    }
}
