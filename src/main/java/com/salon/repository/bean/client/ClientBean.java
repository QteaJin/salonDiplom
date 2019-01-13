package com.salon.repository.bean.client;

import com.salon.repository.entity.address.Address;
import com.salon.repository.entity.checklist.CheckList;
import com.salon.repository.entity.discount.Discount;
import com.salon.repository.entity.profile.Profile;
import com.salon.repository.entity.salon.Salon;
import com.salon.utility.EnumRole;
import com.salon.utility.EnumStatus;

import java.util.List;

public class ClientBean {
    private Long id;
    private Address address;
    private Discount discount;
    private Salon salon;
    private EnumRole role;
    private Profile profile;
    private EnumStatus status;
    private List<CheckList> checkList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public EnumRole getRole() {
        return role;
    }

    public void setRole(EnumRole role) {
        this.role = role;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    public List<CheckList> getCheckList() {
        return checkList;
    }

    public void setCheckList(List<CheckList> checkList) {
        this.checkList = checkList;
    }
}
