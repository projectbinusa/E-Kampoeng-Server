package com.data.penduduk.service;

import com.data.penduduk.model.Rt;
import com.data.penduduk.repository.RtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImplRt implements UserDetailsService {
    @Autowired
    RtRepository rtRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Rt rt = rtRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email : " + email + ", Not Found"));
        return UserDetailsImplRt.build(rt);
    }
}
