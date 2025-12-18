package com.project.peps.country.service;

import com.project.peps.country.dto.CountryDTO;
import com.project.peps.country.dto.CountryRequest;
import com.project.peps.country.mapper.CountryMapper;
import com.project.peps.country.model.Country;
import com.project.peps.country.repository.CountryRepository;
import com.project.peps.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryMapper countryMapper;

    public List<CountryDTO> getAllCountries() {
        return countryRepository.findAll().stream()
                .map(countryMapper::toCountryDTO)
                .collect(Collectors.toList());
    }

    public CountryDTO getCountryById(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with id " + id));
        return countryMapper.toCountryDTO(country);
    }

    public CountryDTO createCountry(CountryRequest countryRequest) {
        Country country = countryMapper.toCountry(countryRequest);
        country = countryRepository.save(country);
        return countryMapper.toCountryDTO(country);
    }

    public CountryDTO updateCountry(Long id, CountryRequest countryRequest) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with id " + id));
        country.setName(countryRequest.getName());
        country.setImageUrl(countryRequest.getImageUrl());
        country = countryRepository.save(country);
        return countryMapper.toCountryDTO(country);
    }

    public void deleteCountry(Long id) {
        if (!countryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Country not found with id " + id);
        }
        countryRepository.deleteById(id);
    }
}
