package com.e_kampoeng.service;

import com.e_kampoeng.model.RWModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface RWService {
    RWModel add(RWModel rwModel);

    Page<RWModel> getAll(Pageable pageable);

    RWModel getById(Long id);

    RWModel update(Long id, RWModel rwModel);

    Map<String, Boolean> remove(Long id);
}
