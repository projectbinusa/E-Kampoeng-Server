package com.e_kampoeng.service;

import com.e_kampoeng.dao.RTRepository;
import com.e_kampoeng.dao.WargaDao;
import com.e_kampoeng.dao.WilayahRTRepository;
import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.RTModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RTImpl implements RTService{

    @Autowired
    RTRepository rtRepository;

    @Autowired
    WargaDao wargaDao;

    @Autowired
    WilayahRTRepository wilayahRTRepository;

    @Override
    public RTModel add(RTModel rtModel) {
        RTModel rtModel1 = new RTModel();
        rtModel1.setCreate_at(rtModel.getCreate_at());
        rtModel1.setWarga(wargaDao.findById(rtModel.getWarga().getId()).orElseThrow(() -> new NotFoundException("id not found")));
        rtModel1.setWilayahRT(wilayahRTRepository.findById(rtModel.getWilayahRT().getId()).orElseThrow(() -> new NotFoundException("id not found")));
        return rtRepository.save(rtModel);
    }

    @Override
    public Page<RTModel> getAll(Long page, Long limit, String search, String sort) {
        Sort.Direction direction = Sort.Direction.ASC;
        if (sort.startsWith("-")) {
            sort = sort.substring(1);
            direction = Sort.Direction.DESC;
        }

        Pageable pageable = PageRequest.of(Math.toIntExact(page - 1), Math.toIntExact(limit), direction, sort);
        if (search != null && !search.isEmpty()) {
            return rtRepository.findAllByKeyword(search, pageable);
        } else {
            return rtRepository.findAll(pageable);
        }
    }

    @Override
    public RTModel getById(Long id) {
        return rtRepository.findById(id).orElseThrow(() -> new NotFoundException("id not found"));
    }

    @Override
    public RTModel putData(Long id, RTModel rtModel) {
        RTModel update = rtRepository.findById(id).orElseThrow(() -> new NotFoundException("id not found"));
        update.setWilayahRT(rtModel.getWilayahRT());
        update.setWarga(rtModel.getWarga());
        return rtRepository.save(update);
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        try {
            rtRepository.deleteById(id);
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.TRUE);
            return res;
        } catch (Exception e) {
            throw new NotFoundException("id not found");
        }
    }
}
