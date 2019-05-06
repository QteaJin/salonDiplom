package com.salon.repository.entity.worker;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.salon.repository.entity.abstractEntity.AbstractUser;
import com.salon.repository.entity.checklist.CheckList;
import com.salon.repository.entity.profile.Profile;
import com.salon.repository.entity.salon.Salon;
import com.salon.repository.entity.skills.Skills;
import com.salon.repository.entity.worktime.WorkTime;
import com.salon.utility.EnumRole;
import com.salon.utility.EnumStatus;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "worker")
public class Worker extends AbstractUser implements Serializable {
    private Long id;

    private List<Skills> skillsList = new ArrayList<>();

    private List<CheckList> checkLists = new ArrayList<>();
     
    private List<WorkTime> schedule = new ArrayList<>();

    private Salon salon;

    public Worker() {

    }

    public Worker(EnumRole role, Profile profile,
                  EnumStatus status, List<Skills> skillsList, String description, List<CheckList> checkLists, List<WorkTime> schedule ) {
        super(role, profile, status, description);
        this.skillsList = skillsList;
        this.checkLists = checkLists;
        this.schedule = schedule;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToMany()
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonManagedReference
    public List<Skills> getSkillsList() {
        return skillsList;
    }

    public void setSkillsList(List<Skills> skillsList) {
        this.skillsList = skillsList;
    }

    @OneToMany(mappedBy = "worker")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonBackReference
    public List<CheckList> getCheckLists() {
        return checkLists;
    }

    public void setCheckLists(List<CheckList> checkLists) {
        this.checkLists = checkLists;
    }

    @ManyToOne()
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "salon_id")
    @JsonBackReference
    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }
    
    @ManyToMany(cascade = {CascadeType.ALL})
    @JsonManagedReference
	public List<WorkTime> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<WorkTime> schedule) {
		this.schedule = schedule;
	}
    
    
}
