package com.salon.repository.entity.worktime;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.salon.repository.entity.salon.Salon;
import com.salon.repository.entity.worker.Worker;
import com.salon.utility.EnumStatus;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "work_time")
public class WorkTime implements Serializable{
    private Long id;
    private Timestamp startWorking;
    private Timestamp finishWorking;
    private EnumStatus status;
    private Timestamp date;

    private List<Salon> salons = new ArrayList<>();
    
    private List<Worker> workers = new ArrayList<>();

    public WorkTime() {
    }

    public WorkTime(Long id, Timestamp startWorking, Timestamp finishWorking,
                    EnumStatus status, Timestamp date, List<Salon> salons, List<Worker> workers) {
        this.id = id;
        this.startWorking = startWorking;
        this.finishWorking = finishWorking;
        this.status = status;
        this.date = date;
        this.salons = salons;
        this.workers = workers;
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

    @Column(name = "start_working")
    public Timestamp getStartWorking() {
        return startWorking;
    }

    public void setStartWorking(Timestamp startWorking) {
        this.startWorking = startWorking;
    }

    @Column(name = "finish_working")
    public Timestamp getFinishWorking() {
        return finishWorking;
    }

    public void setFinishWorking(Timestamp finishWorking) {
        this.finishWorking = finishWorking;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @ManyToMany(mappedBy = "timeList")
    @JsonBackReference
    public List<Salon> getSalons() {
        return salons;
    }

    public void setSalons(List<Salon> salons) {
        this.salons = salons;
    }
    
    @ManyToMany(mappedBy = "schedule")
    @JsonBackReference
	public List<Worker> getWorkers() {
		return workers;
	}

	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}
    
}
