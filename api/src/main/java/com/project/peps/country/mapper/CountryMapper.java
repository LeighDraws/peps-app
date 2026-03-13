package com.project.peps.country.mapper;

import com.project.peps.country.dto.CountryRequest;
import com.project.peps.country.dto.CountryResponse;
import com.project.peps.country.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CountryMapper {

    CountryResponse toCountryResponse(Country country);

    List<CountryResponse> toCountryResponseList(List<Country> countries);

    Country toCountry(CountryRequest countryRequest);

    void updateEntityFromRequest(CountryRequest request, @MappingTarget Country entity);
}