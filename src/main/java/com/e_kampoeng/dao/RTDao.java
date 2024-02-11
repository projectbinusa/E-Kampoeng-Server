package com.e_kampoeng.dao;

import com.e_kampoeng.model.RTModel;
import com.e_kampoeng.model.RWModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RTDao extends CrudRepository<RTModel, Integer> {

    RWModel findById(long id);
}
