package com.e_kampoeng.service;

import com.e_kampoeng.dao.ESoeratRepository;
import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.ESoeratModel;
import com.e_kampoeng.model.WilayahRTModel;
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
public class ESoeratServiceImpl implements ESoeratService {

    @Autowired
    ESoeratRepository eSoeratRepository;

    @Override
    public ESoeratModel addSoerat(ESoeratModel eSoeratModel) {
        ESoeratModel soerat = new ESoeratModel();
        soerat.setJenis_surat(eSoeratModel.getJenis_surat());
        soerat.setJenis_bantuan(eSoeratModel.getJenis_bantuan());
        return eSoeratRepository.save(soerat);
    }

    @Override
    public ESoeratModel editSoerat(Long id, ESoeratModel eSoeratModel) {
        ESoeratModel eSoeratModel1 = eSoeratRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found"));
        eSoeratModel1.setJenis_surat(eSoeratModel.getJenis_surat());
        eSoeratModel1.setJenis_bantuan(eSoeratModel.getJenis_bantuan());
        return eSoeratRepository.save(eSoeratModel1);
    }


//    @Override
//    public Page<ESoeratModel> getAllSoerat(Long page, Long limit, String search, String sort) {
//        Sort.Direction direction = Sort.Direction.ASC;
//        if (sort.startsWith("-")) {
//            sort = sort.substring(1);
//            direction = Sort.Direction.DESC;
//        }
//
//        Pageable pageable = PageRequest.of(Math.toIntExact(page - 1), Math.toIntExact(limit), direction, sort);
//        if (search != null && !search.isEmpty()) {
//            return eSoeratRepository.findAllByKeyword(search, pageable);
//        } else {
//            return eSoeratRepository.findAll(pageable);
//        }
//    }
    @Override
    public ESoeratModel getIdSoerat(Long id) {
        var soerat = eSoeratRepository.findById(id).get();
        return eSoeratRepository.findById(id).orElseThrow(() -> new NotFoundException("id not found"));
    }


    @Override
    public Map<String, Boolean> deleteSoerat(Long id) {
        try {
            eSoeratRepository.deleteById(id);
            Map<String, Boolean> res = new HashMap<>();
            res.put("Hapus", Boolean.TRUE);
            return res;
        } catch (Exception e) {

            Map<String, Boolean> tes = new HashMap<>();
            tes.put("Hapus", Boolean.FALSE);
            return tes;
        }
    }

//    @Override
//    public List<ESoeratModel> allSoerat() {
//        return eSoeratRepository.findAll();
//    }

}
