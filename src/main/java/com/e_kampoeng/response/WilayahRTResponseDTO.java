package com.e_kampoeng.response;

public class WilayahRTResponseDTO {
    private Long id;
    private Long nomor_rt;
    private Long wilayah_rw_id;

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

    public Long getWilayah_rw_id() {
        return wilayah_rw_id;
    }

    public void setWilayah_rw_id(Long wilayah_rw_id) {
        this.wilayah_rw_id = wilayah_rw_id;
    }
}