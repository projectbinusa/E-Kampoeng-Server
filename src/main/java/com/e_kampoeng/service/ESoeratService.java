package com.e_kampoeng.service;

import com.e_kampoeng.model.ESoeratModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ESoeratService {
    Page<ESoeratModel> getAllSoerat(Pageable pageable);
    ESoeratModel getIdSoerat(Long id);
    ESoeratModel addSoerat(ESoeratModel eSoeratModel);
    ESoeratModel editSoerat(Long id,ESoeratModel eSoeratModel);
    Map<String ,Boolean> deleteSoerat(Long id);


}
