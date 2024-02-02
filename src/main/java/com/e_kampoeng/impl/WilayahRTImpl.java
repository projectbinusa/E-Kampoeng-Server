package com.e_kampoeng.impl;

import com.e_kampoeng.repository.WilayahRTRepository;
import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.service.WilayahRTService;
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
public class WilayahRTImpl implements WilayahRTService {

    @Autowired
    private WilayahRTRepository wilayahRTRepository;



    @Override
    public WilayahRTModel add(WilayahRTModel wilayahRTModel) {
        WilayahRTModel wilayahRTModel1 = new WilayahRTModel();
        wilayahRTModel1.setNomor_rt(wilayahRTModel1.getNomor_rt());
        return wilayahRTRepository.save(wilayahRTModel);
    }

    @Override
    public Page<WilayahRTModel> getAll(Long page, Long limit, String search, String sort) {
        Sort.Direction direction = Sort.Direction.ASC;
        if (sort.startsWith("-")) {
            sort = sort.substring(1);
            direction = Sort.Direction.DESC;
        }

        Pageable pageable = PageRequest.of(Math.toIntExact(page - 1), Math.toIntExact(limit), direction, sort);
        if (search != null && !search.isEmpty()) {
            return wilayahRTRepository.findAllByKeyword(search, pageable);
        } else {
            return wilayahRTRepository.findAll(pageable);
        }
    }

    @Override
    public WilayahRTModel getById(Long id) {
        return wilayahRTRepository.findById(id).orElseThrow(() -> new NotFoundException("Id not found"));
    }

    @Override
    public List<WilayahRTModel> preview() {
        return wilayahRTRepository.findAll();
    }

    @Override
    public WilayahRTModel putData(Long id, WilayahRTModel wilayahRTModel) {
        WilayahRTModel update = wilayahRTRepository.findById(id).orElseThrow(() -> new NotFoundException("Id not found"));
        update.setNomor_rt(wilayahRTModel.getNomor_rt());
        return wilayahRTRepository.save(update);
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        try {
            wilayahRTRepository.deleteById(id);
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.TRUE);
            return res;
        } catch (Exception e) {
            throw new NotFoundException("id not found");
        }
    }
}
