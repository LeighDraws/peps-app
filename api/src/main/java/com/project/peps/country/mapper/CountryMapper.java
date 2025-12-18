package com.project.peps.country.mapper;

import org.springframework.stereotype.Component;

import com.project.peps.country.dto.CountryRequest;
import com.project.peps.country.dto.CountryResponse;
import com.project.peps.country.model.Country;

@Component
public class CountryMapper {

    public CountryResponse toCountryResponse(Country country) {
        if (country == null) {
            return null;
        }
        return CountryResponse.builder()
                .id(country.getId())
                .name(country.getName())
                .imageUrl(country.getImageUrl())
                .build();
    }

    public Country toCountry(CountryRequest countryRequest) {
        if (countryRequest == null) {
            return null;
        }
        return Country.builder()
                .name(countryRequest.getName())
                .imageUrl(countryRequest.getImageUrl())
                .build();
    }
}
