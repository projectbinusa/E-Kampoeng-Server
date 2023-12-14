package com.e_kampoeng.service;

import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.model.WilayahRWModel;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface WilayahRTService {
    WilayahRTModel add(WilayahRTModel wilayahRTModel);

    Page<WilayahRTModel> getAll(Long page, Long pageSize, String sort, String sortDirection);

    WilayahRTModel getById(Long id);

    List<WilayahRTModel> preview();

    WilayahRTModel putData(Long id, WilayahRTModel wilayahRTModel);

    Map<String, Boolean> delete(Long id);
}
