package com.e_kampoeng.service;

import com.e_kampoeng.dao.WilayahRWRepository;
import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.WilayahRWModel;
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
public class WilayahRWImpl implements WilayahRWService {

    @Autowired
    WilayahRWRepository wilayahRWRepository;


    @Override
    public WilayahRWModel addWilayahRW(WilayahRWModel wilayahRWModel) {
        WilayahRWModel wilayahRWModel1 = new WilayahRWModel();
        wilayahRWModel1.setNomor_rw(wilayahRWModel.getNomor_rw());
        wilayahRWModel1.setNama_dusun(wilayahRWModel.getNama_dusun());
        return wilayahRWRepository.save(wilayahRWModel1);
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
                return wilayahRWRepository.findAllByKeyword(search, pageable);
            } else {
                return wilayahRWRepository.findAll(pageable);
            }
    }

    @Override
    public WilayahRWModel getByIdWilayahRW(Long id) {
        return wilayahRWRepository.findById(id).orElseThrow(() -> new NotFoundException("Id not found"));
    }

    @Override
    public List<WilayahRWModel> previewWilayahRW() {
        return wilayahRWRepository.findAll();
    }

    @Override
    public WilayahRWModel putDataWilayahRW(Long id, WilayahRWModel wilayahRWModel) {
        WilayahRWModel update = wilayahRWRepository.findById(id).orElseThrow(() -> new NotFoundException("Id not found"));
        update.setNomor_rw(wilayahRWModel.getNomor_rw());
        update.setNama_dusun(wilayahRWModel.getNama_dusun());
        return wilayahRWRepository.save(update);
    }

    @Override
    public Map<String, Boolean> deleteWilayahRW(Long id) {
        try {
            wilayahRWRepository.deleteById(id);
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.TRUE);
            return res;
        } catch (Exception e) {
            throw new NotFoundException("id not found");
        }
    }
}
