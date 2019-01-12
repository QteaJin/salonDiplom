package com.salon.repository.dao.role;

import com.salon.repository.entity.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, Long> {
}
