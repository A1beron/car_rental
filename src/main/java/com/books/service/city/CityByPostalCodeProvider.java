package com.books.service.city;

import com.books.api.address.City;

import java.util.Set;

public interface CityByPostalCodeProvider {

    Set<City> getCityNamesFromPostalCode(String postalCode);

}
