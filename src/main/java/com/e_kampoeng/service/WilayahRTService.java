package com.e_kampoeng.service;

import com.e_kampoeng.model.WilayahRTModel;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface WilayahRTService {
    WilayahRTModel addWilayahRt(WilayahRTModel wilayahRTModel);

    WilayahRTModel putWilayahRt(Long id, WilayahRTModel wilayahRTModel);

    WilayahRTModel getByIdWilayahRt(Long id);

    Map<String, Boolean> deleteWilayahRt(Long id);

    Page<WilayahRTModel> getAllWilayahRt(Long page, Long pageSize, String sort, String sortDirectioh);
}
