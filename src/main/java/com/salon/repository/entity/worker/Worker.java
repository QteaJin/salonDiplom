package com.salon.repository.entity.worker;

import com.salon.repository.entity.abstractEntity.user.AbstractUser;
import com.salon.repository.entity.checklist.CheckList;
import com.salon.repository.entity.profile.Profile;
import com.salon.repository.entity.role.Role;
import com.salon.repository.entity.salon.Salon;
import com.salon.repository.entity.skills.Skills;
import com.salon.utility.EnumStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "worker")
public class Worker extends AbstractUser implements Serializable {
    private Long id;
    private List<Skills> skillsList;
    private List<CheckList> checkLists;
    private Salon salon;

    public Worker() {

    }

    public Worker(Long id, Role role, Profile profile,
                  EnumStatus status, List<Skills> skillsList) {
        super(role, profile, status);
        this.id = id;
        this.skillsList = skillsList;
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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "worker_skills",
            joinColumns = @JoinColumn(name = "worker_id"),
            inverseJoinColumns = @JoinColumn(name = "skills_id"))
    public List<Skills> getSkillsList() {
        return skillsList;
    }

    public void setSkillsList(List<Skills> skillsList) {
        this.skillsList = skillsList;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "worker_check_list",
            joinColumns = {@JoinColumn(name = "worker_id")},
            inverseJoinColumns = {@JoinColumn(name = "check_list_id")})
    public List<CheckList> getCheckLists() {
        return checkLists;
    }

    public void setCheckLists(List<CheckList> checkLists) {
        this.checkLists = checkLists;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salon_id")
    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }
}
