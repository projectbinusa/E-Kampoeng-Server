package com.e_kampoeng.service;

import com.e_kampoeng.model.RTModel;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.repository.RTRepository;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.repository.WilayahRTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

public interface RTService {

    RTModel tambahKepalaRT(Long wilayahRTId, Long wargaId);
    RTModel updateRT(Long rtId, Long wargaId, Long nomorRT);
    Page<RTModel> getAllRT(Pageable pageable);
    RTModel getRTById(Long id);
    Map<String, Boolean> deleteRT(Long id);

}