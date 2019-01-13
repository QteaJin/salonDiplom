package com.salon.service.google.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salon.repository.bean.adress.AdressBean;
import com.salon.repository.bean.google.GoogleGeocodingCountryCity;
import com.salon.repository.bean.google.GoogleGeocogingLatLng;
import com.salon.service.google.GoogleLocationService;
import com.salon.utility.Const;
import com.salon.utility.HTTPRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;

@Service
public class GoogleLocationServiceImpl implements GoogleLocationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleLocationServiceImpl.class);

    @Value("${google.api.key}")
    private String ApiKey;

    @Override
    public AdressBean receiveAddressFromGoogle() {
        AdressBean adressBean = new AdressBean();

        GoogleGeocogingLatLng latLng = deserializationLatLng(
                HTTPRequestService.requestPost(createURL(Const.GoogleApi.URL_GEOCODING_API)));

        GoogleGeocodingCountryCity countryCity = deserializationCountryCity(
                HTTPRequestService.requestPost(createUrlLatLng(latLng.getLat(), latLng.getLng(), ApiKey)));

        adressBean.setCity(countryCity.getCity());
        adressBean.setCountry(countryCity.getCountry());
        adressBean.setLat(Double.valueOf(latLng.getLat()));
        adressBean.setLng(Double.valueOf(latLng.getLng()));
        return adressBean;
    }


    private String createURL(String firstUrl) {
        StringBuilder builder = new StringBuilder(firstUrl);
        builder.append(ApiKey);
        return builder.toString();
    }

    private String createUrlLatLng(String lat, String lng, String apikey) {
        StringBuilder builder = new StringBuilder(Const.GoogleApi.URL_GOOGLELOCATION);

        if (lat != null && lng != null) {
            builder.append(lat);
            builder.append(",");
            builder.append(lng);
        }

        if (apikey != null) {
            builder.append("&key=");
            builder.append(apikey);
        }

        return builder.toString();
    }

    private GoogleGeocogingLatLng deserializationLatLng(String json) {
        GoogleGeocogingLatLng geocodingJson = new GoogleGeocogingLatLng();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);
            JsonNode node = jsonNode.path("location");
            geocodingJson = objectMapper.readValue(node.toString(), GoogleGeocogingLatLng.class);
        } catch (IOException e) {
            LOGGER.debug("deserialization lat and lng fail " + e.getMessage());
        }
        return geocodingJson;
    }

    private GoogleGeocodingCountryCity deserializationCountryCity(String json) {
        GoogleGeocodingCountryCity locationBean = new GoogleGeocodingCountryCity();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);
            JsonNode results = jsonNode.get("results");
            JsonNode address_components = results.get(0);
            Iterator<JsonNode> elements = address_components.elements();
            Iterator<JsonNode> list = elements.next().elements();
            while (list.hasNext()) {
                JsonNode node = list.next();
                String long_name = node.get("long_name").textValue();
                JsonNode nodes = node.get("types");
                Iterator<JsonNode> iterator = nodes.elements();
                while (iterator.hasNext()) {
                    JsonNode jsonNode1 = iterator.next();
                    if (jsonNode1.textValue().equals("locality")) {
                        locationBean.setCity(long_name);
                        break;
                    }
                    if (jsonNode1.textValue().equals("country")) {
                        locationBean.setCountry(long_name);
                        break;
                    }
                    if (jsonNode1.textValue().equals("postal_code")) {
                        locationBean.setIndex(long_name);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.debug("deserialization Country and City and index fail " + e.getMessage());
            throw new IllegalArgumentException("deserialization Country and City and index fail");
        }
        return locationBean;
    }
}
