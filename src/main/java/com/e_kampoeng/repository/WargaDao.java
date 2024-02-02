package com.e_kampoeng.repository;

import com.e_kampoeng.model.WargaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WargaDao extends JpaRepository<WargaModel, Long> {
}
