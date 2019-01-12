package com.salon.repository.bean.role;

import com.salon.utility.EnumStatus;

public class RoleBean {
    private Long roleId;
    private String name;
    private String description;
    private EnumStatus enumStatus;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EnumStatus getEnumStatus() {
        return enumStatus;
    }

    public void setEnumStatus(EnumStatus enumStatus) {
        this.enumStatus = enumStatus;
    }
}
