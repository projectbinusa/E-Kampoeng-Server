package com.e_kampoeng.service;

import com.e_kampoeng.model.WargaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface WargaService {
    Page<WargaModel> getAll(Pageable pageable);

    WargaModel getById(Long id);

    WargaModel update(Long id, WargaModel wm);

    WargaModel create(WargaModel warga);

    Map<String, Boolean> delete(Long id);
}
