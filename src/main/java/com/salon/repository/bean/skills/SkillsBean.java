package com.salon.repository.bean.skills;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.salon.repository.entity.catalog.Catalog;
import com.salon.repository.entity.worker.Worker;

import java.util.ArrayList;
import java.util.List;

public class SkillsBean {
    private Long skillsId;
    private String name;

    private List<Catalog> catalogList = new ArrayList<>();
    private List<Worker> workers = new ArrayList<>();

    public Long getSkillsId() {
        return skillsId;
    }

    public void setSkillsId(Long skillsId) {
        this.skillsId = skillsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    public List<Catalog> getCatalogList() {
        return catalogList;
    }

    public void setCatalogList(List<Catalog> catalogList) {
        this.catalogList = catalogList;
    }

    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }
}
