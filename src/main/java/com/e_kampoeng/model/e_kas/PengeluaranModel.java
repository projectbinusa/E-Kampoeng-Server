package com.e_kampoeng.model.e_kas;

import com.e_kampoeng.config.DateConfig;
import com.e_kampoeng.model.WilayahRTModel;

import javax.persistence.*;

@Entity
@Table(name = "pengeluaran")
public class PengeluaranModel extends DateConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kegiatan")
    private String kegiatan;

    @Column(name = "anggaran_keluar")
    private Double anggaranKeluar;
    @ManyToOne
    @JoinColumn(name = "wilayah_rt_id")
    private WilayahRTModel wilayahRT;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKegiatan() {
        return kegiatan;
    }

    public void setKegiatan(String kegiatan) {
        this.kegiatan = kegiatan;
    }

    public Double getAnggaranKeluar() {
        return anggaranKeluar;
    }

    public void setAnggaranKeluar(Double anggaranKeluar) {
        this.anggaranKeluar = anggaranKeluar;
    }

    public WilayahRTModel getWilayahRT() {
        return wilayahRT;
    }

    public void setWilayahRT(WilayahRTModel wilayahRT) {
        this.wilayahRT = wilayahRT;
    }
}
