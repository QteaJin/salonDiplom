package com.salon.repository.entity.catalog;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.salon.repository.entity.checklist.CheckList;
import com.salon.repository.entity.skills.Skills;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "catalog")
public class Catalog implements Serializable{
    private Long catalogId;
    private String name;
    private String description;
    private Double price;
    private Long timeLead;

    private Skills skills;

    private List<CheckList> checkLists = new ArrayList<>();

    public Catalog() {
    }

    public Catalog(Long catalogId, String name, String description,
                   Double price, Long timeLead, List<CheckList> checkLists,
                   Skills skills) {
        this.catalogId = catalogId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.timeLead = timeLead;
        this.checkLists = checkLists;
        this.skills = skills;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catalog_id", nullable = false, unique = true)
    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "time_lead")
    public Long getTimeLead() {
        return timeLead;
    }

    public void setTimeLead(Long timeLead) {
        this.timeLead = timeLead;
    }

    @ManyToMany()
    @JsonBackReference
    public List<CheckList> getCheckLists() {
        return checkLists;
    }

    public void setCheckLists(List<CheckList> checkLists) {
        this.checkLists = checkLists;
    }

    @ManyToOne()
    @JoinColumn(name = "skills_id")
    @JsonBackReference
    public Skills getSkills() {
        return skills;
    }

    public void setSkills(Skills skills) {
        this.skills = skills;
    }
}
