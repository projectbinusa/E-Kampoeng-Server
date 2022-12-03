package com.data.penduduk.repository;

import com.data.penduduk.model.Rt;
import com.data.penduduk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RtRepository extends JpaRepository<Rt, Long> {
    Optional<Rt> findByUsername(String username);
    Boolean existsByUsername(String username);

    List<Rt> findRtByUser(User user);

    @Query("SELECT p FROM Rt p WHERE CONCAT(p.username) LIKE %?1%")
    List<Rt> searchByUsername(String username);

}
