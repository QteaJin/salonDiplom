package com.salon.repository.entity.worker;

import com.salon.repository.entity.abstractEntity.user.AbstractUser;
import com.salon.repository.entity.profile.Profile;
import com.salon.repository.entity.role.Role;
import com.salon.repository.entity.skills.Skills;
import com.salon.utility.EnumStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "worker")
public class Worker extends AbstractUser implements Serializable {
    private Long id;
    private List<Skills> skills;

    public Worker() {

    }

    public Worker(Long id, Role role, Profile profile, EnumStatus status, List<Skills> skills) {
        super(role, profile, status);
        this.skills = skills;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "worker_id", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "worker_skills", joinColumns = {
            @JoinColumn(name = "worker_id", nullable = false, unique = true)},
            inverseJoinColumns = {
            @JoinColumn(name = "skills_id", nullable = false, unique = true)
            })
    public List<Skills> getSkills() {
        return skills;
    }

    public void setSkills(List<Skills> skills) {
        this.skills = skills;
    }
}
