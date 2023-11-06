package com.data.penduduk.repository;

import com.data.penduduk.model.RtModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RtRepository extends JpaRepository<RtModel, Long> {
    @Query(value = "SELECT * FROM rt  WHERE name  LIKE CONCAT('%',:keyword, '%') OR active LIKE CONCAT('%',:keyword, '%')", nativeQuery = true)
    Page<RtModel> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
