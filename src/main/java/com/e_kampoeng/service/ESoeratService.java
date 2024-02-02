package com.e_kampoeng.service;

import com.e_kampoeng.model.ESoeratModel;
import com.e_kampoeng.model.WilayahRWModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ESoeratService {
//    Page<ESoeratModel> getAllSoerat(String query, Long page);


//    Page<ESoeratModel>getAllSoerat(Long page, Long pageSize, String sort, String sortDirection);

    ESoeratModel getIdSoerat(Long id);

    ESoeratModel addSoerat(ESoeratModel eSoeratModel);

    ESoeratModel editSoerat(Long id,ESoeratModel eSoeratModel);

    Map<String ,Boolean> deleteSoerat(Long id);
//    List<ESoeratModel> allSoerat();
}
