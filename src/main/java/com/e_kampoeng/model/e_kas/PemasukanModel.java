package com.e_kampoeng.model.e_kas;


import com.e_kampoeng.config.DateConfig;
import com.e_kampoeng.model.WilayahRTModel;

import javax.persistence.*;

@Entity
@Table(name = "pemasukan")
public class PemasukanModel extends DateConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nama;
    private Double jumlahPemasukan;
    @ManyToOne
    @JoinColumn(name = "wilayah_rt_id")
    private WilayahRTModel wilayahRT;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Double getJumlahPemasukan() {
        return jumlahPemasukan;
    }

    public void setJumlahPemasukan(Double jumlahPemasukan) {
        this.jumlahPemasukan = jumlahPemasukan;
    }

    public WilayahRTModel getWilayahRT() {
        return wilayahRT;
    }

    public void setWilayahRT(WilayahRTModel wilayahRT) {
        this.wilayahRT = wilayahRT;
    }
}
