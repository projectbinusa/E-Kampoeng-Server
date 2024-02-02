package com.e_kampoeng.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "warga")
public class WargaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nama;
    @Column
    private String tempat_lahir;
    @Column
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date tanggal_lahir;
    @Column
    private String jenis_kelamin;
    @Column
    private String agama;
    @Column
    private Integer nik;
    @Column
    private String no_kk;
    @Column
    private String status_dalam_keluarga;
    @Column
    private String status_kependudukan;
    @Column
    private String pendidikan;
    @Column
    private String pekerjaan;
    @Column
    private String status_perkawinan;
    @Column
    private String golongan_darah;
    @Column
    private String jenis_asuransi;
    @Column
    private String jenis_kb;
    @Column
    private String kesesuaian_tempat;
    @Column
    private String sumber_air;

    @ManyToOne
    @JoinColumn(name = "wilayah_rt_id")
    private WilayahRTModel wilayah_rt;

    @ManyToOne
    @JoinColumn(name = "wilayah_rw_id")
    private WilayahRWModel wilayah_rw;

    @OneToMany(mappedBy = "warga", cascade = CascadeType.ALL)
    private List<WargaOrganisasiModel> wargas = new ArrayList<>();

    //    getter setter

    public WargaModel() {
    }

    public WargaModel(Long id, String nama, String tempat_lahir, Date tanggal_lahir, String jenis_kelamin, String agama, Integer nik, String no_kk, String status_dalam_keluarga, String status_kependudukan, String pendidikan, String pekerjaan, String status_perkawinan, String golongan_darah, String jenis_asuransi, String jenis_kb, String kesesuaian_tempat, String sumber_air, WilayahRTModel wilayah_rt, WilayahRWModel wilayah_rw, List<WargaOrganisasiModel> wargas) {
        this.id = id;
        this.nama = nama;
        this.tempat_lahir = tempat_lahir;
        this.tanggal_lahir = tanggal_lahir;
        this.jenis_kelamin = jenis_kelamin;
        this.agama = agama;
        this.nik = nik;
        this.no_kk = no_kk;
        this.status_dalam_keluarga = status_dalam_keluarga;
        this.status_kependudukan = status_kependudukan;
        this.pendidikan = pendidikan;
        this.pekerjaan = pekerjaan;
        this.status_perkawinan = status_perkawinan;
        this.golongan_darah = golongan_darah;
        this.jenis_asuransi = jenis_asuransi;
        this.jenis_kb = jenis_kb;
        this.kesesuaian_tempat = kesesuaian_tempat;
        this.sumber_air = sumber_air;
        this.wilayah_rt = wilayah_rt;
        this.wilayah_rw = wilayah_rw;
        this.wargas = wargas;
    }

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

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public Date getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(Date tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public Integer getNik() {
        return nik;
    }

    public void setNik(Integer nik) {
        this.nik = nik;
    }

    public String getNo_kk() {
        return no_kk;
    }

    public void setNo_kk(String no_kk) {
        this.no_kk = no_kk;
    }

    public String getStatus_dalam_keluarga() {
        return status_dalam_keluarga;
    }

    public void setStatus_dalam_keluarga(String status_dalam_keluarga) {
        this.status_dalam_keluarga = status_dalam_keluarga;
    }

    public String getStatus_kependudukan() {
        return status_kependudukan;
    }

    public void setStatus_kependudukan(String status_kependudukan) {
        this.status_kependudukan = status_kependudukan;
    }

    public String getPendidikan() {
        return pendidikan;
    }

    public void setPendidikan(String pendidikan) {
        this.pendidikan = pendidikan;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getStatus_perkawinan() {
        return status_perkawinan;
    }

    public void setStatus_perkawinan(String status_perkawinan) {
        this.status_perkawinan = status_perkawinan;
    }

    public String getGolongan_darah() {
        return golongan_darah;
    }

    public void setGolongan_darah(String golongan_darah) {
        this.golongan_darah = golongan_darah;
    }

    public String getJenis_asuransi() {
        return jenis_asuransi;
    }

    public void setJenis_asuransi(String jenis_asuransi) {
        this.jenis_asuransi = jenis_asuransi;
    }

    public String getJenis_kb() {
        return jenis_kb;
    }

    public void setJenis_kb(String jenis_kb) {
        this.jenis_kb = jenis_kb;
    }

    public String getKesesuaian_tempat() {
        return kesesuaian_tempat;
    }

    public void setKesesuaian_tempat(String kesesuaian_tempat) {
        this.kesesuaian_tempat = kesesuaian_tempat;
    }

    public String getSumber_air() {
        return sumber_air;
    }

    public void setSumber_air(String sumber_air) {
        this.sumber_air = sumber_air;
    }

    public WilayahRTModel getWilayah_rt() {
        return wilayah_rt;
    }

    public void setWilayah_rt(WilayahRTModel wilayah_rt) {
        this.wilayah_rt = wilayah_rt;
    }

    public List<WargaOrganisasiModel> getWargas() {
        return wargas;
    }

    public void setWargas(List<WargaOrganisasiModel> wargas) {
        this.wargas = wargas;
    }


    public WilayahRWModel getWilayah_rw() {
        return wilayah_rw;
    }

    public void setWilayah_rw(WilayahRWModel wilayah_rw) {
        this.wilayah_rw = wilayah_rw;
    }
}