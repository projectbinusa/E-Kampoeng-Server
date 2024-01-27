package com.e_kampoeng.service;

import com.e_kampoeng.model.RTModel;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface RTService {

    RTModel add(RTModel rtModel);

    Page<RTModel> getAll(Long page, Long pageSize, String sort, String sortDirection);

    RTModel getById(Long id);

    RTModel putData(Long id, RTModel rtModel);

    Map<String, Boolean> delete(Long id);
}
