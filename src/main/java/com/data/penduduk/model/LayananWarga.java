package com.data.penduduk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "layanan_warga")
public class LayananWarga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama")
    private String nama;

    @Column(name = "tgl_buat")
    private Date tgl_buat;

    @Column(name = "tgl_diperlukan")
    private  Date tgl_diperlukan;

    @Column(name = "keperluan")
    private String keperluan;

    @JsonIgnore
    @ManyToOne
    private Rt rt;

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

    public Date getTgl_buat() {
        return tgl_buat;
    }

    public void setTgl_buat(Date tgl_buat) {
        this.tgl_buat = tgl_buat;
    }

    public Date getTgl_diperlukan() {
        return tgl_diperlukan;
    }

    public void setTgl_diperlukan(Date tgl_diperlukan) {
        this.tgl_diperlukan = tgl_diperlukan;
    }

    public String getKeperluan() {
        return keperluan;
    }

    public void setKeperluan(String keperluan) {
        this.keperluan = keperluan;
    }

    public Rt getRt() {
        return rt;
    }

    public void setRt(Rt rt) {
        this.rt = rt;
    }
}
