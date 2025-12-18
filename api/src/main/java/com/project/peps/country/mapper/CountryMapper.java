package com.project.peps.country.mapper;

import com.project.peps.country.dto.CountryDTO;
import com.project.peps.country.dto.CountryRequest;
import com.project.peps.country.model.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {

    public CountryDTO toCountryDTO(Country country) {
        if (country == null) {
            return null;
        }
        return CountryDTO.builder()
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
