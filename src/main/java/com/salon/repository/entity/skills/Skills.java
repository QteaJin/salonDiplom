package com.salon.repository.entity.skills;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.salon.repository.entity.catalog.Catalog;
import com.salon.repository.entity.worker.Worker;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "skills")
public class Skills implements Serializable {

    private Long skillsId;
    private String name;

    private List<Catalog> catalogList = new ArrayList<>();

    private List<Worker> workers = new ArrayList<>();

    public Skills() {
    }

    public Skills(Long skillsId, String name,
                  List<Catalog> catalogList, List<Worker> workers) {
        this.skillsId = skillsId;
        this.name = name;
        this.catalogList = catalogList;
        this.workers = workers;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skills_id", nullable = false, unique = true)
    public Long getSkillsId() {
        return skillsId;
    }

    public void setSkillsId(Long skillsId) {
        this.skillsId = skillsId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "skills")
    @JsonManagedReference
    public List<Catalog> getCatalogList() {
        return catalogList;
    }

    public void setCatalogList(List<Catalog> catalogList) {
        this.catalogList = catalogList;
    }

    @ManyToMany(mappedBy = "skillsList")
    @JsonBackReference
    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }
}
