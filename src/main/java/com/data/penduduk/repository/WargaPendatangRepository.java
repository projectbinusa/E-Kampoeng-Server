package com.data.penduduk.repository;

import com.data.penduduk.model.WargaPendatang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WargaPendatangRepository extends JpaRepository<WargaPendatang, Long> {

    @Override
    List<WargaPendatang> findAll();

}
