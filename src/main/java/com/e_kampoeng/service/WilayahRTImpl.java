package com.e_kampoeng.service;

import com.e_kampoeng.dao.WilayahRTRepository;
import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.WilayahRTModel;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WilayahRTImpl implements WilayahRTService{

    @Autowired
    private WilayahRTRepository wilayahRTRepository;

    @Override
    public WilayahRTModel addWilayahRt(WilayahRTModel wilayahRTModel) {
        WilayahRTModel wilayahRTModel1 = new WilayahRTModel();
        wilayahRTModel1.setNomor_rt(wilayahRTModel.getNomor_rt());
        return wilayahRTRepository.save(wilayahRTModel1);
    }

    @Override
    public WilayahRTModel putWilayahRt(Long id, WilayahRTModel wilayahRTModel) {
        WilayahRTModel update = wilayahRTRepository.findById(id).orElseThrow(() -> new NotFoundException("Id Not Found"));
        update.setNomor_rt(wilayahRTModel.getNomor_rt());
        return wilayahRTRepository.save(update);
    }

    @Override
    public WilayahRTModel getByIdWilayahRt(Long id) {
        return wilayahRTRepository.findById(id).orElseThrow(() -> new NotFoundException("Id Not Found"));
    }

    @Override
    public Map<String, Boolean> deleteWilayahRt(Long id) {
        try {
            wilayahRTRepository.deleteById(id);
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.TRUE);
            return res;
        } catch (Exception e) {
            throw new NotFoundException("id not found");
        }
    }

    @Override
    public Page<WilayahRTModel> getAllWilayahRt(Long page, Long limit, String search, String sort) {
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
}
