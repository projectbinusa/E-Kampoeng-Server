package com.data.penduduk.repository;

import com.data.penduduk.model.KkModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KkRepository extends JpaRepository<KkModel, Long> {

//    @Query(value = "SELECT * FROM e_kampoeng  WHERE name  LIKE CONCAT('%',:keyword, '%') OR active LIKE CONCAT('%',:keyword, '%')", nativeQuery = true)
//    Page<KkModel> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
