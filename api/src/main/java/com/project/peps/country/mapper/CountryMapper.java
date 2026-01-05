package com.project.peps.country.mapper;

import com.project.peps.country.dto.CountryRequest;
import com.project.peps.country.dto.CountryResponse;
import com.project.peps.country.model.Country;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<CountryResponse> toCountryResponseList(List<Country> countries) {
        if (countries == null) {
            return Collections.emptyList();
        }
        return countries.stream()
                .map(this::toCountryResponse)
                .collect(Collectors.toList());
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

