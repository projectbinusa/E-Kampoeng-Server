package com.e_kampoeng.dao;

import com.e_kampoeng.model.RWModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RWDao extends CrudRepository<RWModel, Integer> {

    RWModel findById(long id);
}
