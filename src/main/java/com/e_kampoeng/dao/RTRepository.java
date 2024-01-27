package com.e_kampoeng.dao;

import com.e_kampoeng.model.RTModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RTRepository extends JpaRepository<RTModel, Long> {
    @Query(value = "SELECT * FROM rt WHERE id_warga LIKE CONCAT('%',:keyword, '%') OR id_wilayah_rt LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
    Page<RTModel> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);

}
