package com.salon.service.role;

import com.salon.repository.bean.role.RoleBean;
import com.salon.repository.entity.role.Role;
import com.salon.service.CRUDService;

public interface RoleService extends CRUDService<RoleBean,Role> {
    RoleBean findByName(String name);
}
