package com.project.peps.country.controller;

import com.project.peps.country.dto.CountryRequest;
import com.project.peps.country.dto.CountryResponse;
import com.project.peps.country.mapper.CountryMapper;
import com.project.peps.country.model.Country;
import com.project.peps.country.service.CountryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;
    private final CountryMapper countryMapper;

    public CountryController(CountryService countryService, CountryMapper countryMapper) {
        this.countryService = countryService;
        this.countryMapper = countryMapper;
    }

    @GetMapping
    public ResponseEntity<List<CountryResponse>> getAllCountries() {
        List<Country> countries = countryService.findAllCountries();
        return ResponseEntity.ok(countryMapper.toCountryResponseList(countries));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryResponse> getCountryById(@PathVariable Long id) {
        Country country = countryService.findCountryById(id);
        return ResponseEntity.ok(countryMapper.toCountryResponse(country));
    }



    @PostMapping
    public ResponseEntity<CountryResponse> createCountry(@Valid @RequestBody CountryRequest countryRequest) {
        Country createdCountry = countryService.createCountry(countryRequest);
        return new ResponseEntity<>(countryMapper.toCountryResponse(createdCountry), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryResponse> updateCountry(@PathVariable Long id, @Valid @RequestBody CountryRequest countryRequest) {
        Country updatedCountry = countryService.updateCountry(id, countryRequest);
        return ResponseEntity.ok(countryMapper.toCountryResponse(updatedCountry));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return ResponseEntity.noContent().build();
    }
}

