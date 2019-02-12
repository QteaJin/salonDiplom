package com.salon.repository.entity.abstractEntity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.salon.repository.entity.profile.Profile;
import com.salon.utility.EnumRole;
import com.salon.utility.EnumStatus;

import javax.persistence.*;

@MappedSuperclass
public class AbstractUser {
    private EnumRole role;
    private Profile profile;
    private EnumStatus status;
    private String description;

    public AbstractUser() {
    }

    public AbstractUser(EnumRole role, Profile profile, EnumStatus status, String description) {
        this.role = role;
        this.profile = profile;
        this.status = status;
        this.description = description;
    }

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    public EnumRole getRole() {
        return role;
    }

    public void setRole(EnumRole role) {
        this.role = role;
    }

    @OneToOne
    @JsonManagedReference
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
