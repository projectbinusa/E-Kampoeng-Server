package com.e_kampoeng.repository;

import com.e_kampoeng.model.CategoryBerita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryBeritaRepository extends CrudRepository<CategoryBerita, Integer> {
    CategoryBerita findById(long id);
    CategoryBerita getById(Long id);
    Page<CategoryBerita> findAll(Pageable pageable);
    Page<CategoryBerita> findAllByOrderByUpdatedDateDesc(Pageable pageable);
    Page<CategoryBerita> findAllByWilayahRTId(Long wilayahRTId, Pageable pageable);
    Optional<CategoryBerita> findByIdAndWilayahRTId(Long id, Long wilayahRTId);
    void deleteByIdAndWilayahRTId(Long id, Long wilayahRTId);
}
