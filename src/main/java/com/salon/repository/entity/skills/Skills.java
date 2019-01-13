package com.salon.repository.entity.skills;

import com.salon.repository.entity.catalog.Catalog;
import com.salon.repository.entity.worker.Worker;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "skills")
public class Skills {

    private Long skillsId;
    private String name;

    private List<Catalog> catalogList;
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

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "skills_catalog",
            joinColumns = {@JoinColumn(name = "skills_id")},
            inverseJoinColumns = {@JoinColumn(name = "catalog_id")})
    public List<Catalog> getCatalogList() {
        return catalogList;
    }

    public void setCatalogList(List<Catalog> catalogList) {
        this.catalogList = catalogList;
    }

    @ManyToMany(mappedBy = "skillsList")
    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }
}
