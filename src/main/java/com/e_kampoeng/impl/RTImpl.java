package com.e_kampoeng.impl;

import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.RTModel;
import com.e_kampoeng.repository.RTRepository;
import com.e_kampoeng.service.RTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RTImpl implements RTService {

    @Autowired
    private RTRepository rtRepository;

    @Override
    public Page<RTModel> getAll(Pageable pageable) {
        return rtRepository.findAll(pageable);
    }

    @Override
    public RTModel getById(Long id) {
        RTModel rtm = rtRepository.findById(id).orElse(null);

        if (rtm == null) {
            throw new NotFoundException("RT Id not found");
        }
        return rtm;
    }

    @Override
    public RTModel post(RTModel rtModel) {
        RTModel rtm = new RTModel();
        rtm.setWarga(rtModel.getWarga());
        rtm.setWilRT(rtModel.getWilRT());

        return rtRepository.save(rtm);
    }

    @Override
    public RTModel put(RTModel rtModel, Long id) {
        RTModel rtm = rtRepository.findById(id).orElse(null);
        if (rtm == null) {
            throw new NotFoundException("RT Id not found");
        }
        rtm.setWarga(rtModel.getWarga());
        rtm.setWilRT(rtModel.getWilRT());

        return rtRepository.save(rtm);
    }

    @Override
    public Map<String, Boolean> del(Long id) {
        try {
            rtRepository.deleteById(id);
            Map<String, Boolean> obj = new HashMap<>();
            obj.put("Deleted", Boolean.TRUE);
            return obj;
        } catch (Exception e) {
            throw new NotFoundException("RT Id not found");
        }
    }
}
