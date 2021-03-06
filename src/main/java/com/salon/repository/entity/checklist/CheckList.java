package com.salon.repository.entity.checklist;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.salon.repository.entity.catalog.Catalog;
import com.salon.repository.entity.client.Client;
import com.salon.repository.entity.worker.Worker;
import com.salon.utility.EnumStatus;
import com.salon.utility.EnumStatusCheckList;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "check_list")
public class CheckList implements Serializable {
    private Long sheckListId;
    private Timestamp timeFinish;
    private Double price;
    private EnumStatusCheckList status;
    private Timestamp dateCreate;
    private Timestamp dateAppointment; // Date on when order was assigned
    private String description;

    private Worker worker;

    private Client client;

    private List<Catalog> catalogs = new ArrayList<>();

    public CheckList() {
    }

    public CheckList(Long sheckListId, Timestamp timeFinish, Double price, EnumStatusCheckList status,
                     Timestamp dateCreate, Timestamp dateAppointment, Worker worker, Client client,  List<Catalog> catalogs,
                     String description) {
        this.sheckListId = sheckListId;
        this.timeFinish = timeFinish;
        this.price = price;
        this.status = status;
        this.dateCreate = dateCreate;
        this.dateAppointment = dateAppointment;
        this.worker = worker;
        this.client = client;
        this.catalogs = catalogs;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_list_id", nullable = false, unique = true)
    public Long getSheckListId() {
        return sheckListId;
    }

    public void setSheckListId(Long sheckListId) {
        this.sheckListId = sheckListId;
    }

    @Column(name = "time_finish")
    public Timestamp getTimeFinish() {
        return timeFinish;
    }

    public void setTimeFinish(Timestamp timeFinish) {
        this.timeFinish = timeFinish;
    }

    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public EnumStatusCheckList getStatus() {
        return status;
    }

    public void setStatus(EnumStatusCheckList status) {
        this.status = status;
    }

    @Column(name = "date_create")
    public Timestamp getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Timestamp dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Column(name = "date_appointment")
    public Timestamp getDateAppointment() {
        return dateAppointment;
    }

    public void setDateAppointment(Timestamp dateAppointment) {
        this.dateAppointment = dateAppointment;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne()
    @JoinColumn(name = "worker_id")
    @JsonManagedReference
    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @ManyToOne()
    @JoinColumn(name = "client_id")
    @JsonManagedReference
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @ManyToMany(mappedBy = "checkLists")
    @JsonManagedReference
    public List<Catalog> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(List<Catalog> catalogs) {
        this.catalogs = catalogs;
    }
}
