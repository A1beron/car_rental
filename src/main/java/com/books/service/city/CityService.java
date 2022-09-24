package com.books.service.city;

import com.books.api.address.City;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Set;

public class CityService implements CityByPostalCodeProvider {

    @Override
    public Set<City> getCityNamesFromPostalCode(String postalCode) {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI("http://kodpocztowy.intami.pl/api/" + postalCode))
                    .setHeader("content-type", "application/json")
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.body(), new TypeReference<Set<City>>(){});

        } catch (IOException | URISyntaxException | InterruptedException e) {
            e.printStackTrace();
            return Set.of();
        }
    }
}
