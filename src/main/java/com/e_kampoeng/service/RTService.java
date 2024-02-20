package com.e_kampoeng.service;

import com.e_kampoeng.model.RTModel;

import java.util.List;
import java.util.Optional;

public interface RTService {
    RTModel assignRT(Long wargaId, Long nomorRT);
    RTModel updateRT(Long rtId, Long wargaId, Long nomorRT);
    List<RTModel> getAllRT();
    Optional<RTModel> getRTById(Long id);
    void deleteRT(Long id);
}
