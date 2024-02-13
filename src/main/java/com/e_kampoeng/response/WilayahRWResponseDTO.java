package com.e_kampoeng.response;

public class WilayahRWResponseDTO {
    private Long id;
    private Long nomor_rw;
    private String nama_dusun;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNomor_rw() {
        return nomor_rw;
    }

    public void setNomor_rw(Long nomor_rw) {
        this.nomor_rw = nomor_rw;
    }

    public String getNama_dusun() {
        return nama_dusun;
    }

    public void setNama_dusun(String nama_dusun) {
        this.nama_dusun = nama_dusun;
    }
}
