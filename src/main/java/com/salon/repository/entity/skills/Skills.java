package com.salon.repository.entity.skills;

import javax.persistence.*;

@Entity
@Table(name = "skills")
public class Skills {

    private Long skillsId;
    private String name;

    public Skills() {
    }

    public Skills(Long skillsId, String name) {
        this.skillsId = skillsId;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
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
}
