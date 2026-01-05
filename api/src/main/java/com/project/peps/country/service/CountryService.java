package com.project.peps.country.service;

import java.util.List;

import com.project.peps.country.dto.CountryRequest;
import com.project.peps.country.model.Country;

public interface CountryService {

    List<Country> findAllCountries();

    Country findCountryById(Long id);

    Country createCountry(CountryRequest countryRequest);

    Country updateCountry(Long id, CountryRequest countryRequest);

    void deleteCountry(Long id);
}