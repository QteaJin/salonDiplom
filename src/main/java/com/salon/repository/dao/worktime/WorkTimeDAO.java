package com.salon.repository.dao.worktime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salon.repository.entity.worktime.WorkTime;

public interface WorkTimeDAO extends JpaRepository<WorkTime, Long>{

}
