package com.e_kampoeng.repository;

import com.e_kampoeng.model.Tags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagsRepository extends JpaRepository<Tags, Long> {
    Page<Tags> findAllByWilayahRTId(Long wilayahRTId, Pageable pageable);
    Optional<Tags> findByIdAndWilayahRTId(Long id, Long wilayahRTId);
}
