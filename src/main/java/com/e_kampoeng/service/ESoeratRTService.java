package com.e_kampoeng.service;

import com.e_kampoeng.model.ESoeratModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Optional;

public interface ESoeratRTService {
    Page<ESoeratModel> getAllSoerat(Pageable pageable);
    ESoeratModel getIdSoerat(Long id);
    ESoeratModel addSoerat(ESoeratModel eSoeratModel);
    ESoeratModel editSoerat(Long id, ESoeratModel eSoeratModel);
    Map<String ,Boolean> deleteSoerat(Long id);
    Optional<ESoeratModel> getSoeratByIdAndCreator(Long id);
}
