package com.data.penduduk.service;

import com.data.penduduk.exception.NotFoundException;
import com.data.penduduk.model.KkModel;
import com.data.penduduk.model.RtModel;
import com.data.penduduk.repository.RtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RtImpl implements RtService{

    @Autowired
    private RtRepository rtRepository;

    @Override
    public RtModel add(RtModel rtModel) {
        return rtRepository.save(rtModel);
    }

    @Override
    public Page<RtModel> getAll(Long page, Long limit, String sort, String search) {
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
    public RtModel getId(Long id) {
        return rtRepository.findById(id).orElseThrow(() -> new RuntimeException("Id Not Found"));
    }

    @Override
    public RtModel edit(RtModel rtModel, Long id) {
        RtModel update = rtRepository.findById(id).orElseThrow(() ->new NotFoundException("Id Not Found"));
        update.setNama_rt(rtModel.getNama_rt());
        update.setWarga_id(rtModel.getWarga_id());
        return rtRepository.save(update);
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        try {
            rtRepository.deleteById(id);
            Map<String, Boolean> res = new HashMap<>();
            res.put("deleted", Boolean.TRUE);
            return res;
        } catch (Exception e) {
            throw new NotFoundException("Id Not Found");
        }
    }
}
