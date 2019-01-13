package com.salon.repository.entity.address;

import com.salon.repository.entity.client.Client;
import com.salon.repository.entity.salon.Salon;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "address")
public class Address {
    private Long addressId;
    private String street;
    private String country;
    private String city;
    private Double lat;
    private Double lng;

    private List<Salon> salonList;
    private List<Client> clientsList;


    public Address() {
    }

    public Address(Long addressId, String street, String country,
                   String city, Double lat, Double lng, List<Salon> salonList,
                   List<Client> clientsList) {
        this.addressId = addressId;
        this.street = street;
        this.country = country;
        this.city = city;
        this.lat = lat;
        this.lng = lng;

        this.salonList=salonList;
        this.clientsList=clientsList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false, unique = true)
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

    @Column(name = "country", nullable = false)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "city", nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "lat")
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Column(name = "lng")
    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "address_worker",
            joinColumns = {@JoinColumn(name = "address_id")},
            inverseJoinColumns = {@JoinColumn(name = "worker_id", nullable = false)})
    public List<Salon> getSalonList() {
        return salonList;
    }

    public void setSalonList(List<Salon> salonList) {
        this.salonList = salonList;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
