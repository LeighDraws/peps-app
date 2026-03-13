package com.project.peps.country.service;

import com.project.peps.country.model.Country;
import java.util.List;

public interface CountryService {

    List<Country> findAllCountries();

    Country findCountryById(Long id);

    Country save(Country country);

    void deleteCountry(Long id);
}