package com.e_kampoeng.repository;

import com.e_kampoeng.model.RTModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RTRepository extends JpaRepository<RTModel, Long> {
    Optional<RTModel> findByNomorRT(Long nomorRT);

}
