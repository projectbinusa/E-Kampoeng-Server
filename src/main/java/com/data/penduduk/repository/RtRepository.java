package com.data.penduduk.repository;

import com.data.penduduk.model.Rt;
import com.data.penduduk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RtRepository extends JpaRepository<Rt, Long> {
    Optional<Rt> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}
