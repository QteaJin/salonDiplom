package com.salon.repository.entity.abstractEntity.user;

import com.salon.repository.entity.profile.Profile;
import com.salon.repository.entity.role.Role;
import com.salon.utility.EnumStatus;

import javax.persistence.*;

@MappedSuperclass
public class AbstractUser {
    private Role role;
    private Profile profile;
    private EnumStatus status;

    public AbstractUser() {
    }

    public AbstractUser( Role role, Profile profile, EnumStatus status) {
        this.role = role;
        this.profile = profile;
        this.status = status;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "role_id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
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
}
