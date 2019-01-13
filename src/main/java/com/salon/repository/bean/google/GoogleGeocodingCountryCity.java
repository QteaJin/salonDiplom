package com.salon.repository.bean.google;

public class GoogleGeocodingCountryCity {
    private String country;
    private String city;
    private String index;

    public GoogleGeocodingCountryCity() {
    }

    public GoogleGeocodingCountryCity(String country, String city, String index) {
        this.country = country;
        this.city = city;
        this.index = index;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
