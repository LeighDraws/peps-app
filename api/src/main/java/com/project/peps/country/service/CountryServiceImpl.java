package com.project.peps.country.service;

import com.project.peps.country.dto.CountryRequest;
import com.project.peps.country.model.Country;
import com.project.peps.country.repository.CountryRepository;
import com.project.peps.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country findCountryById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "id", id));
    }

    @Override
    public Country createCountry(CountryRequest countryRequest) {
        Country country = Country.builder()
                .name(countryRequest.getName())
                .imageUrl(countryRequest.getImageUrl())
                .build();
        return countryRepository.save(country);
    }

    @Override
    public Country updateCountry(Long id, CountryRequest countryRequest) {
        Country existingCountry = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "id", id));
        existingCountry.setName(countryRequest.getName());
        existingCountry.setImageUrl(countryRequest.getImageUrl());
        return countryRepository.save(existingCountry);
    }

    @Override
    public void deleteCountry(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "id", id));
        countryRepository.delete(country);
    }
}