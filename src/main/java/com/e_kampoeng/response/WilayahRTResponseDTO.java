package com.e_kampoeng.response;

import com.e_kampoeng.model.WilayahRWModel;

public class WilayahRTResponseDTO {
    private Long id;
    private Long nomor_rt;
    private WilayahRWResponseDTO wilayah_rw;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNomor_rt() {
        return nomor_rt;
    }

    public void setNomor_rt(Long nomor_rt) {
        this.nomor_rt = nomor_rt;
    }

    public WilayahRWResponseDTO getWilayah_rw() {
        return wilayah_rw;
    }

    public void setWilayah_rw(WilayahRWResponseDTO wilayah_rw) {
        this.wilayah_rw = wilayah_rw;
    }
}