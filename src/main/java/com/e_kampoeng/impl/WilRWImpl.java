package com.e_kampoeng.impl;

import com.e_kampoeng.repository.WilayahRWDao;
import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.WilayahRWModel;
import com.e_kampoeng.service.WilayahRWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WilRWImpl implements WilayahRWService {

    @Autowired
    WilayahRWDao wilayahRWDao;


    @Override
    public WilayahRWModel addWilayahRW(WilayahRWModel wilayahRWModel) {
        WilayahRWModel wilayahRWModel1 = new WilayahRWModel();
        wilayahRWModel1.setNomor_rw(wilayahRWModel.getNomor_rw());
        wilayahRWModel1.setNama_dusun(wilayahRWModel.getNama_dusun());
        return wilayahRWDao.save(wilayahRWModel);
    }

    @Override
    public Page<WilayahRWModel> getAllWilayahRW(Long page, Long limit, String search, String sort) {
        Sort.Direction direction = Sort.Direction.ASC;
        if (sort.startsWith("-")) {
            sort = sort.substring(1);
            direction = Sort.Direction.DESC;
        }

        Pageable pageable = PageRequest.of(Math.toIntExact(page - 1), Math.toIntExact(limit), direction, sort);
            if (search != null && !search.isEmpty()) {
                return wilayahRWDao.findAllByKeyword(search, pageable);
            } else {
                return wilayahRWDao.findAll(pageable);
            }
    }

    @Override
    public WilayahRWModel getByIdWilayahRW(Long id) {
        return wilayahRWDao.findById(id).orElseThrow(() -> new NotFoundException("Id not found"));
    }

    @Override
    public List<WilayahRWModel> previewWilayahRW() {
        return wilayahRWDao.findAll();
    }

    @Override
    public WilayahRWModel putDataWilayahRW(Long id, WilayahRWModel wilayahRWModel) {
        WilayahRWModel update = wilayahRWDao.findById(id).orElseThrow(() -> new NotFoundException("Id not found"));
        update.setNomor_rw(wilayahRWModel.getNomor_rw());
        update.setNama_dusun(wilayahRWModel.getNama_dusun());
        return wilayahRWDao.save(update);
    }

    @Override
    public Map<String, Boolean> deleteWilayahRW(Long id) {
        try {
            wilayahRWDao.deleteById(id);
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.TRUE);
            return res;
        } catch (Exception e) {
            throw new NotFoundException("id not found");
        }
    }
}
