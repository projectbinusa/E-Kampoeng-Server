package com.data.penduduk.service;

import com.data.penduduk.model.ESoeratModel;
import com.data.penduduk.model.KkModel;

import java.util.List;
import java.util.Map;

public interface KkService {

    KkModel add(KkModel kkModel);

    KkModel edit(KkModel kkModel, Long id);

    KkModel get(Long id);

    List<KkModel> getAll();

    Map<String, Boolean> delete(Long id);

}
