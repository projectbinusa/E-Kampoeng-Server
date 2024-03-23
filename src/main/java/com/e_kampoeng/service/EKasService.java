package com.e_kampoeng.service;

import com.e_kampoeng.dto.EKasDTO;
import com.e_kampoeng.model.EKasModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface EKasService {
    Page<EKasModel> findAllWithPagination(Pageable pageable);
    EKasModel findById(Long id);
    EKasModel create(EKasDTO eKasDTO);
    EKasModel update(Long id, EKasModel eKasModel);
    Map<String, Boolean> delete(Long id);
}
