package com.salon.repository.dao.client;

import com.salon.repository.entity.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDAO extends JpaRepository<Client, Long> {
	
}
