package com.data.penduduk.service;

import com.data.penduduk.model.RtModel;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface RtService {

    RtModel add(RtModel rtModel);

    Page<RtModel> getAll(Long page, Long pageSize, String sort, String sortDirection);

    RtModel getId(Long id);

    RtModel edit(RtModel rtModel, Long id);

    Map<String, Boolean> delete(Long id);

}
