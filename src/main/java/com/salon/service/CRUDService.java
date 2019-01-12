package com.salon.service;

import java.io.Serializable;
import java.util.List;

public interface CRUDService <D,B extends Serializable> {

    D save(D bean);

    List<D> findAll();

    D findById(Long id);

    D update(D bean);

    void delete(D bean);

    List<D> findByExample(D bean);

    B toBean(D domain);

    D toDomain(B bean);

}
