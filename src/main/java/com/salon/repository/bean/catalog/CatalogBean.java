package com.salon.repository.bean.catalog;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.salon.repository.entity.checklist.CheckList;
import com.salon.repository.entity.skills.Skills;

public class CatalogBean {

    private Long catalogId;
    private String name;
    private String description;
    private Double price;
    private Long timeLead;

    private Skills skills;
    private List<CheckList> checkLists = new ArrayList<>();

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getTimeLead() {
        return timeLead;
    }

    public void setTimeLead(Long timeLead) {
        this.timeLead = timeLead;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Skills getSkills() {
        return skills;
    }

    public void setSkills(Skills skills) {
        this.skills = skills;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public List<CheckList> getCheckLists() {
        return checkLists;
    }

    public void setCheckLists(List<CheckList> checkLists) {
        this.checkLists = checkLists;
    }


}
