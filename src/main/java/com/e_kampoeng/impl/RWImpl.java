package com.e_kampoeng.impl;

import com.e_kampoeng.repository.RWRepository;
import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.RWModel;
import com.e_kampoeng.service.RWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class RWImpl implements RWService {

    @Autowired
    private RWRepository rwDao;

//    menambahkan data warga yang menjabat sbg rw
    @Override
    public RWModel add(RWModel rwModel) {
        RWModel newData = new RWModel();
        newData.setWarga(rwModel.getWarga());
//        newData.setWilayahRW(rwModel.getWilayahRW());
        newData.setWilRW(rwModel.getWilRW());
        newData.setCreate_at(new Date());

        return rwDao.save(rwModel);
    }

    @Override
    public Page<RWModel> getAll(Pageable pageable) {
        return rwDao.findAll(pageable);
    }

    @Override
    public RWModel getById(Long id) {
        return rwDao.findById(id).orElse(null);
    }

    @Override
    public RWModel update(Long id, RWModel rwModel) {
        RWModel update = rwDao.findById(id).orElse(null);
        update.setWarga(rwModel.getWarga());
        update.setWilRW(rwModel.getWilRW());
        return rwDao.save(update);
    }

    @Override
    public Map<String, Boolean> remove(Long id) {
        try {
            rwDao.deleteById(id);
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.TRUE);
            return res;
        } catch (Exception e) {
            throw new NotFoundException("RW id not found");
        }
    }
}
