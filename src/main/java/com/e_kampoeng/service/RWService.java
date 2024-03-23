package com.e_kampoeng.service;

import com.e_kampoeng.model.RWModel;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.repository.RWRepository;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.repository.WilayahRWRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;


public interface RWService {
    Page<RWModel> getAllRW(Pageable pageable);
    RWModel getRWById(Long id);
    RWModel tambahKepalaRW(Long wilayahRWId, Long wargaId);
    RWModel updateRW(Long rwId, Long wargaId);
    Map<String, Boolean> deleteRW(Long id);
}
