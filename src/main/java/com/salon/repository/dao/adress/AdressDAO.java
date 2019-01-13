package com.salon.repository.dao.adress;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salon.repository.entity.address.Address;

public interface AdressDAO extends JpaRepository<Address, Long> {

}
