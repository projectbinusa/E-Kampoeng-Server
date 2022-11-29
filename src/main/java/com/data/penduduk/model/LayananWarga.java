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

    @Column(name = "email")
    private String email;

    @Column(name = "nama")
    private String nama;

    @Column(name = "tempat_lahir")
    private String tempat_lahir;

    @Column(name = "tgl_lahir")
    private  Date tgl_lahir;

    @Column(name = "gender")
    private String gender;

    @Column(name = "pekerjaan")
    private String pekerjaan;

    @Column(name = "agama")
    private String agama;

    @Column(name = "status")
    private String status;

    @Column(name = "kewarganegaraan")
    private String kewarganegaraan;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "keperluan")
    private String keperluan;

//    @JsonIgnore
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public Date getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(Date tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKewarganegaraan() {
        return kewarganegaraan;
    }

    public void setKewarganegaraan(String kewarganegaraan) {
        this.kewarganegaraan = kewarganegaraan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
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
