package com.e_kampoeng.service;

import com.e_kampoeng.model.WilayahRWModel;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface WilayahRWService {
    WilayahRWModel addWilayahRW(WilayahRWModel wilayahRWModel);

    Page<WilayahRWModel>getAllWilayahRW(Long page, Long pageSize, String sort, String sortDirection);

    WilayahRWModel getByIdWilayahRW(Long id);

    List<WilayahRWModel> previewWilayahRW();

    WilayahRWModel putDataWilayahRW(Long id, WilayahRWModel wilayahRWModel);

    Map<String, Boolean> deleteWilayahRW(Long id);
}
