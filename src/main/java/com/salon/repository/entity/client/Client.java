package com.salon.repository.entity.client;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.salon.repository.entity.abstractEntity.AbstractUser;
import com.salon.repository.entity.address.Address;
import com.salon.repository.entity.checklist.CheckList;
import com.salon.repository.entity.comment.Comment;
import com.salon.repository.entity.discount.Discount;
import com.salon.repository.entity.profile.Profile;
import com.salon.repository.entity.salon.Salon;
import com.salon.utility.EnumRole;
import com.salon.utility.EnumStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client extends AbstractUser implements Serializable {
    private Long id;
    private Address address;
    private Discount discount;
    private Salon salon;
    private List<CheckList> checkList = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();

    public Client() {

    }

    public Client(EnumRole role, Profile profile, EnumStatus status,
                  Long id, Address address, Discount discount, String descroption) {
        super(role, profile, status, descroption);
        this.id = id;
        this.address = address;
        this.discount = discount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne()
    @JoinColumn(name = "address_id")
    @JsonManagedReference
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @OneToOne()
    @JoinColumn(name = "discount_id")
    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    @ManyToOne()
    @JoinColumn(name = "salon_id")
    @JsonBackReference
    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    @OneToMany(mappedBy = "client")
    @JsonBackReference
    public List<CheckList> getCheckList() {
        return checkList;
    }

    public void setCheckList(List<CheckList> checkList) {
        this.checkList = checkList;
    }
    
    @OneToMany(mappedBy = "client")
    @JsonBackReference
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
    
}
