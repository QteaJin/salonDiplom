package com.salon.repository.entity.salon;

import com.salon.repository.entity.address.Address;
import com.salon.repository.entity.client.Client;
import com.salon.repository.entity.worker.Worker;
import com.salon.repository.entity.worktime.WorkTime;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "salon")
public class Salon implements Serializable{
    private Long salonId;
    private String name;
    private String description;
    private Address address;
    private List<WorkTime> timeList;
    private List<Worker> workerList;
    private List<Client> clientList;

    public Salon() {

    }

    public Salon(Long salonId, String name, String description,
                 Address address) {
        this.salonId = salonId;
        this.name = name;
        this.description = description;
        this.address = address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salon_id", nullable = false, unique = true)
    public Long getSalonId() {
        return salonId;
    }

    public void setSalonId(Long salonId) {
        this.salonId = salonId;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne()
    @JoinColumn(name = "address_id")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @ManyToMany()
    @JoinTable(name = "salon_work_time",
            joinColumns = {@JoinColumn(name = "salon_id")},
            inverseJoinColumns = {@JoinColumn(name = "work_time_id")})
    public List<WorkTime> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<WorkTime> timeList) {
        this.timeList = timeList;
    }

    @OneToMany()
    @JoinTable(name = "salon_worker",
            joinColumns = {@JoinColumn(name = "salon_id")},
            inverseJoinColumns = {@JoinColumn(name = "worker_id")})
    public List<Worker> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(List<Worker> workerList) {
        this.workerList = workerList;
    }

    @OneToMany()
    @JoinTable(name = "salon_client",
            joinColumns = {@JoinColumn(name = "salon_id")},
            inverseJoinColumns = {@JoinColumn(name = "client_id")})
    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }
}
