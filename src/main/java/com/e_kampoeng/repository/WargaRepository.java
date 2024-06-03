package com.e_kampoeng.repository;

import com.e_kampoeng.model.Berita;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.model.e_kas.PemasukanModel;
import com.e_kampoeng.request.WilayahRTRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WargaRepository extends JpaRepository<WargaModel, Long> {
    WargaModel findById(long id);

    Page<WargaModel> findAllByWilayahRTId(Long wilayahRTId, Pageable pageable);
    Page<WargaModel> findByWilayahRT_Id(Long rtId, Pageable pageable);
    WargaModel findByEmail(String email);

    List<WargaModel> findAllByWilayahRT(WilayahRTModel wilayahRT);

    @Query("SELECT w FROM WargaModel w WHERE w.role = :role")
    List<WargaModel> findByRole(String role);
}
