package com.data.penduduk.service;

import com.data.penduduk.model.ESoeratModel;

import java.util.List;
import java.util.Map;

public interface ESoeratService {

    ESoeratModel add(ESoeratModel eSoeratModel);

    ESoeratModel edit(ESoeratModel eSoeratModel, Long id);

    ESoeratModel get(Long id);

    List<ESoeratModel> getAll();

    Map<String, Boolean> delete(Long id);

}
