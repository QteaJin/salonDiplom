package com.salon.controller.role;

import com.salon.repository.bean.role.RoleBean;
import com.salon.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RoleBean findById(@PathVariable("id") long id) {
        return roleService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public RoleBean createRole(@RequestBody RoleBean role) {
        return roleService.save(role);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteRole(@PathVariable("id") long id) {
        RoleBean role = roleService.findById(id);
        roleService.delete(role);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public RoleBean updateRole(@RequestBody RoleBean roleBean) {
        return roleService.update(roleBean);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<RoleBean> findAllRole() {
        return roleService.findAll();
    }

}
