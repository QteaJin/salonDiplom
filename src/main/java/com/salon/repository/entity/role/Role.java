package com.salon.repository.entity.role;

import com.salon.utility.EnumStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role" )
public class Role implements Serializable {
    private Long roleId;
    private String name;
    private String description;
    private EnumStatus enumStatus;


    public Role() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public EnumStatus getEnumStatus() {
        return enumStatus;
    }

    public void setEnumStatus(EnumStatus enumStatus) {
        this.enumStatus = enumStatus;
    }

}
