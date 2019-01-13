package com.salon.repository.entity.worktime;

import com.salon.repository.entity.salon.Salon;
import com.salon.utility.EnumStatus;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "work_time")
public class WorkTime {
    private Long id;
    private Timestamp startWorking;
    private Timestamp finishWorking;
    private EnumStatus status;
    private Date date;

    public WorkTime() {
    }

    public WorkTime(Long id, Timestamp startWorking, Timestamp finishWorking,
                    EnumStatus status, Date date) {
        this.id = id;
        this.startWorking = startWorking;
        this.finishWorking = finishWorking;
        this.status = status;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_time_id", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "start_working", nullable = false)
    public Timestamp getStartWorking() {
        return startWorking;
    }

    public void setStartWorking(Timestamp startWorking) {
        this.startWorking = startWorking;
    }

    @Column(name = "finish_working", nullable = false)
    public Timestamp getFinishWorking() {
        return finishWorking;
    }

    public void setFinishWorking(Timestamp finishWorking) {
        this.finishWorking = finishWorking;
    }

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
