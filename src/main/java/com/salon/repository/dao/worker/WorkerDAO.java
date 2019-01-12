package com.salon.repository.dao.worker;

import com.salon.repository.entity.worker.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerDAO extends JpaRepository<Worker, Long> {
}
