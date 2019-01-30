package com.salon.repository.entity.address;

import com.salon.repository.entity.client.Client;
import com.salon.repository.entity.salon.Salon;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "address")
public class Address implements Serializable{
    private Long addressId;
    private String street;
    private String country;
    private String city;
    private Double lat;
    private Double lng;

    private Salon salon;

    private List<Client> clientsList;


    public Address() {
    }

    public Address(Long addressId, String street, String country,
                   String city, Double lat, Double lng, Salon salon,
                   List<Client> clientsList) {
        this.addressId = addressId;
        this.street = street;
        this.country = country;
        this.city = city;
        this.lat = lat;
        this.lng = lng;

        this.salon=salon;
        this.clientsList=clientsList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Column(name = "street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "latitude")
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Column(name = "longitude")
    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @OneToOne(mappedBy = "address")
    @JoinColumn(name = "salon_id")
    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    @OneToMany()
    @JoinTable(name = "address_client",
            joinColumns = {@JoinColumn(name = "address_id")},
            inverseJoinColumns = {@JoinColumn(name = "client_id")})
    public List<Client> getClientsList() {
        return clientsList;
    }

    public void setClientsList(List<Client> clientsList) {
        this.clientsList = clientsList;
    }
}
