package com.data.penduduk.service;

import com.data.penduduk.model.Rt;
import com.data.penduduk.model.User;
import com.data.penduduk.repository.RtRepository;
import com.data.penduduk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RtService {

    @Autowired
    RtRepository rtRepository;

    @Autowired
    UserRepository userRepository;


    public List<Rt> getAllRt() {
        return rtRepository.findAll();
    }

    public List<Rt> getRtByUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return rtRepository.findRtByUser(user);
    }

    public Rt getRtById(Long id) {
        return rtRepository.findById(id).orElse(null);
    }

    public void deleteRt(Long id) {
        rtRepository.deleteById(id);
    }


}
