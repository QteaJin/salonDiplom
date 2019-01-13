package com.salon.repository.entity.checklist;

import com.salon.repository.entity.catalog.Catalog;
import com.salon.repository.entity.client.Client;
import com.salon.repository.entity.worker.Worker;
import com.salon.utility.EnumStatus;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "check_list")
public class CheckList {
    private Long sheckListId;
    private Timestamp timeFinish;
    private Double price;
    private EnumStatus status;
    private Date dateCreate;
    private Date dateAppointment; // Date on when order was assigned

    private Worker worker;
    private Client client;
    private Catalog catalog;

    public CheckList() {
    }

    public CheckList(Long sheckListId, Timestamp timeFinish, Double price, EnumStatus status,
                     Date dateCreate, Date dateAppointment, Worker worker, Client client, Catalog catalog) {
        this.sheckListId = sheckListId;
        this.timeFinish = timeFinish;
        this.price = price;
        this.status = status;
        this.dateCreate = dateCreate;
        this.dateAppointment = dateAppointment;
        this.worker = worker;
        this.client = client;
        this.catalog = catalog;
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
    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    @Column(name = "date_create", nullable = false)
    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Column(name = "date_appointment", nullable = false)
    public Date getDateAppointment() {
        return dateAppointment;
    }

    public void setDateAppointment(Date dateAppointment) {
        this.dateAppointment = dateAppointment;
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
