package com.salon.repository.entity.discount;

import com.salon.utility.EnumStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "discount")
public class Discount implements Serializable {
    private Long discountId;
    private String name;
    private Double discount;
    private String description;
    private EnumStatus status;

    public Discount() {
    }

    public Discount(Long discountId, String name,
                    Double discount, String description,
                    EnumStatus status) {
        this.discountId = discountId;
        this.name = name;
        this.discount = discount;
        this.description = description;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disount_id", nullable = false, unique = true)
    public Long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "discount")
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Column(name = "disc")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }
}
