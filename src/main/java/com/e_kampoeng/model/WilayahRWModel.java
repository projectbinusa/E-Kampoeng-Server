package com.e_kampoeng.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wilayah_rw")
public class WilayahRWModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomor_rw")
    private Integer nomor_rw;

    @Column(name = "nama_dusun")
    private String nama_dusun;

    public WilayahRWModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNomor_rw() {
        return nomor_rw;
    }

    public void setNomor_rw(Integer nomor_rw) {
        this.nomor_rw = nomor_rw;
    }

    public String getNama_dusun() {
        return nama_dusun;
    }

    public void setNama_dusun(String nama_dusun) {
        this.nama_dusun = nama_dusun;
    }
}