package com.e_kampoeng.dao;

import com.e_kampoeng.model.WargaModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WargaDao extends CrudRepository<WargaModel, Integer> {

    WargaModel findById(long id);
}
