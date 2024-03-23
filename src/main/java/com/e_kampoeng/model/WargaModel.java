package com.e_kampoeng.model;

import com.e_kampoeng.config.DateConfig;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "warga")
@Entity
public class WargaModel extends DateConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nama;
    @Column
    private String tempat_lahir;
    @Column
    @JsonFormat(pattern = "dd-MM-yyyy")
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
    private Long no_anak;
    @Column
    private Double panjang_lahir;
    @Column
    private Double berat_lahir;
    @Column
    private Long no_passport;
    @Column
    private String nama_ayah;
    @Column
    private String nama_ibu;
    @Column
    private Long no_telp;
    @Column
    private String email;
    @Column
    private String alamat;
    @Column
    private String alamat_sebelumnya;
    @Column
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date tanggal_perkawinan;
    @Column
    private Long no_bpjs;
    @Column
    private String pendidikan_tempuh;
    @Column
    private String pendidikan_terakhir;
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

    public Long getNo_anak() {
        return no_anak;
    }

    public void setNo_anak(Long no_anak) {
        this.no_anak = no_anak;
    }

    public Double getPanjang_lahir() {
        return panjang_lahir;
    }

    public void setPanjang_lahir(Double panjang_lahir) {
        this.panjang_lahir = panjang_lahir;
    }

    public Double getBerat_lahir() {
        return berat_lahir;
    }

    public void setBerat_lahir(Double berat_lahir) {
        this.berat_lahir = berat_lahir;
    }

    public Long getNo_passport() {
        return no_passport;
    }

    public void setNo_passport(Long no_passport) {
        this.no_passport = no_passport;
    }

    public String getNama_ayah() {
        return nama_ayah;
    }

    public void setNama_ayah(String nama_ayah) {
        this.nama_ayah = nama_ayah;
    }

    public String getNama_ibu() {
        return nama_ibu;
    }

    public void setNama_ibu(String nama_ibu) {
        this.nama_ibu = nama_ibu;
    }

    public Long getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(Long no_telp) {
        this.no_telp = no_telp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAlamat_sebelumnya() {
        return alamat_sebelumnya;
    }

    public void setAlamat_sebelumnya(String alamat_sebelumnya) {
        this.alamat_sebelumnya = alamat_sebelumnya;
    }

    public Date getTanggal_perkawinan() {
        return tanggal_perkawinan;
    }

    public void setTanggal_perkawinan(Date tanggal_perkawinan) {
        this.tanggal_perkawinan = tanggal_perkawinan;
    }

    public Long getNo_bpjs() {
        return no_bpjs;
    }

    public void setNo_bpjs(Long no_bpjs) {
        this.no_bpjs = no_bpjs;
    }

    public String getPendidikan_tempuh() {
        return pendidikan_tempuh;
    }

    public void setPendidikan_tempuh(String pendidikan_tempuh) {
        this.pendidikan_tempuh = pendidikan_tempuh;
    }

    public String getPendidikan_terakhir() {
        return pendidikan_terakhir;
    }

    public void setPendidikan_terakhir(String pendidikan_terakhir) {
        this.pendidikan_terakhir = pendidikan_terakhir;
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

    public WilayahRTModel getWilayahRT() {
        return wilayahRT;
    }

    public void setWilayahRT(WilayahRTModel wilayahRT) {
        this.wilayahRT = wilayahRT;
    }
}