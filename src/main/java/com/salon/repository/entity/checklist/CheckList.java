package com.salon.repository.entity.checklist;

import com.salon.repository.entity.catalog.Catalog;
import com.salon.repository.entity.client.Client;
import com.salon.repository.entity.worker.Worker;
import com.salon.utility.EnumStatus;
import com.salon.utility.EnumStatusCheckList;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "check_list")
public class CheckList implements Serializable {
    private Long sheckListId;
    private Timestamp timeFinish;
    private Double price;
    private EnumStatusCheckList status;
    private Date dateCreate;
    private Date dateAppointment; // Date on when order was assigned
    private String description;

    private Worker worker;
    private Client client;
    private Catalog catalog;

    public CheckList() {
    }

    public CheckList(Long sheckListId, Timestamp timeFinish, Double price, EnumStatusCheckList status,
                     Date dateCreate, Date dateAppointment, Worker worker, Client client, Catalog catalog,
                     String description) {
        this.sheckListId = sheckListId;
        this.timeFinish = timeFinish;
        this.price = price;
        this.status = status;
        this.dateCreate = dateCreate;
        this.dateAppointment = dateAppointment;
        this.worker = worker;
        this.client = client;
        this.catalog = catalog;
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

    @Column(name = "date_create", nullable = false)
    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Column(name = "date_appointment")
    public Date getDateAppointment() {
        return dateAppointment;
    }

    public void setDateAppointment(Date dateAppointment) {
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
    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @ManyToOne()
    @JoinColumn(name = "client_id")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @ManyToOne()
    @JoinColumn(name = "catalog_id")
    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }
}
