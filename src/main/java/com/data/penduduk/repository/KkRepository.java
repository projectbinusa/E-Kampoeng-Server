package com.data.penduduk.repository;

import com.data.penduduk.model.Kk;
import com.data.penduduk.model.Rt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KkRepository extends JpaRepository<Kk, Long> {
    List<Kk> findKkByRt(Rt rt);
}
