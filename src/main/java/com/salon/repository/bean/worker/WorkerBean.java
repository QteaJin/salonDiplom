package com.salon.repository.bean.worker;

import com.salon.repository.entity.checklist.CheckList;
import com.salon.repository.entity.profile.Profile;
import com.salon.repository.entity.salon.Salon;
import com.salon.repository.entity.skills.Skills;
import com.salon.utility.EnumRole;
import com.salon.utility.EnumStatus;

import java.util.List;

public class WorkerBean {
    private Long id;
    private EnumRole role;
    private Profile profile;
    private EnumStatus status;
    private List<Skills> skillsList;
    private List<CheckList> checkLists;
    private Salon salon;

    public WorkerBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Skills> getSkillsList() {
        return skillsList;
    }

    public void setSkillsList(List<Skills> skillsList) {
        this.skillsList = skillsList;
    }

    public List<CheckList> getCheckLists() {
        return checkLists;
    }

    public void setCheckLists(List<CheckList> checkLists) {
        this.checkLists = checkLists;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }
}
