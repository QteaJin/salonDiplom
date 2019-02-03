package com.salon.repository.bean.checklist;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.salon.repository.entity.catalog.Catalog;
import com.salon.repository.entity.client.Client;
import com.salon.repository.entity.worker.Worker;
import com.salon.utility.EnumStatusCheckList;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/*
*   dateAppointment,
*   description,
*
*   workerid,
*   clientid,
*   "catalod":[
*       {
*           "idcatalog":5
*       },
*       {
*          "idcatalog":5
*       }
*   ]
* */
public class CheckListBean {
    private Long sheckListId;
    private Timestamp timeFinish;
    private Double price;
    private EnumStatusCheckList status;
    private Timestamp dateCreate;
    private Timestamp dateAppointment;
    private String description;

    private Worker worker;
    private Client client;
    private List<Catalog> catalog = new ArrayList<>();

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

    public Timestamp getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Timestamp dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Timestamp getDateAppointment() {
        return dateAppointment;
    }

    public void setDateAppointment(Timestamp dateAppointment) {
        this.dateAppointment = dateAppointment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    public List<Catalog> getCatalog() {
        return catalog;
    }

    public void setCatalog(List<Catalog> catalog) {
        this.catalog = catalog;
    }
}
