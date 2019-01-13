package com.salon.repository.dao.checklist;

import com.salon.repository.entity.checklist.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckListDAO extends JpaRepository<CheckList, Long> {
}
