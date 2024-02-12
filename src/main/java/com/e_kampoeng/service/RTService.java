package com.e_kampoeng.service;

import com.e_kampoeng.model.RTModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface RTService {
    Page<RTModel> getAll(Pageable pageable);

    RTModel getById(Long id);

    RTModel post(RTModel rtModel);

    RTModel put(RTModel rtModel, Long id);

    Map<String, Boolean> del(Long id);
}
