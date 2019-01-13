package com.salon.repository.bean.checklist;

import com.salon.repository.entity.catalog.Catalog;
import com.salon.repository.entity.client.Client;
import com.salon.repository.entity.worker.Worker;
import com.salon.utility.EnumStatusCheckList;

import java.sql.Date;
import java.sql.Timestamp;

public class CheckListBean {
    private Long sheckListId;
    private Timestamp timeFinish;
    private Double price;
    private EnumStatusCheckList status;
    private Date dateCreate;
    private Date dateAppointment;

    private Worker worker;
    private Client client;
    private Catalog catalog;

    public Long getSheckListId() {
        return sheckListId;
    }

    public void setSheckListId(Long sheckListId) {
        this.sheckListId = sheckListId;
    }

    public Timestamp getTimeFinish() {
        return timeFinish;
    }

    public void setTimeFinish(Timestamp timeFinish) {
        this.timeFinish = timeFinish;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public EnumStatusCheckList getStatus() {
        return status;
    }

    public void setStatus(EnumStatusCheckList status) {
        this.status = status;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateAppointment() {
        return dateAppointment;
    }

    public void setDateAppointment(Date dateAppointment) {
        this.dateAppointment = dateAppointment;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }
}
