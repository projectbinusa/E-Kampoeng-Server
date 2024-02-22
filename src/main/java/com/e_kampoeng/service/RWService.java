package com.e_kampoeng.service;

import com.e_kampoeng.model.RWModel;

import java.util.List;
import java.util.Optional;

public interface RWService {
    List<RWModel> getAllRW();
    Optional<RWModel> getRWById(Long id);
    RWModel assignRW(Long wargaId, Long nomorRW);
    RWModel updateRW(Long rwId, Long wargaId);
    void deleteRW(Long id);
}
