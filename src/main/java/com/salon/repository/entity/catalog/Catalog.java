package com.salon.repository.entity.catalog;

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
    private Timestamp timeLead;

    private Skills skills;

    private List<CheckList> checkLists = new ArrayList<>();

    public Catalog() {
    }

    public Catalog(Long catalogId, String name, String description,
                   Double price, Timestamp timeLead, List<CheckList> checkLists,
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

    @Column(name = "name", nullable = false)
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

    @Column(name = "price", nullable = false)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "time_lead", nullable = false)
    public Timestamp getTimeLead() {
        return timeLead;
    }

    public void setTimeLead(Timestamp timeLead) {
        this.timeLead = timeLead;
    }

    @ManyToMany()
    public List<CheckList> getCheckLists() {
        return checkLists;
    }

    public void setCheckLists(List<CheckList> checkLists) {
        this.checkLists = checkLists;
    }

    @ManyToOne()
    @JoinColumn(name = "skills_id")
    public Skills getSkills() {
        return skills;
    }

    public void setSkills(Skills skills) {
        this.skills = skills;
    }
}
