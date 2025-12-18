package com.project.peps.country.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.peps.country.dto.CountryRequest;
import com.project.peps.country.dto.CountryResponse;
import com.project.peps.country.mapper.CountryMapper;
import com.project.peps.country.model.Country;
import com.project.peps.country.repository.CountryRepository;
import com.project.peps.shared.exception.ResourceNotFoundException;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryMapper countryMapper;

    public List<CountryResponse> getAllCountries() {
        return countryRepository.findAll().stream()
                .map(countryMapper::toCountryResponse)
                .collect(Collectors.toList());
    }

    public CountryResponse getCountryById(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with id " + id));
        return countryMapper.toCountryResponse(country);
    }

    public CountryResponse createCountry(CountryRequest countryRequest) {
        Country country = countryMapper.toCountry(countryRequest);
        country = countryRepository.save(country);
        return countryMapper.toCountryResponse(country);
    }

    public CountryResponse updateCountry(Long id, CountryRequest countryRequest) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with id " + id));
        country.setName(countryRequest.getName());
        country.setImageUrl(countryRequest.getImageUrl());
        country = countryRepository.save(country);
        return countryMapper.toCountryResponse(country);
    }

    public void deleteCountry(Long id) {
        if (!countryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Country not found with id " + id);
        }
        countryRepository.deleteById(id);
    }
}
