package com.data.penduduk.repository;

import com.data.penduduk.model.ESoeratModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ESeoratRepository extends JpaRepository<ESoeratModel, Long> {
}
