package com.data.penduduk.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "berita_lelayu")
public class Lelayu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama")
    private String nama;

    @Column(name = "usia")
    private Integer usia;

    @Column(name = "hari_meninggal")
    private String hari_meninggal;

    @Column(name = "tgl_meninggal")
    private Date tgl_meninggal;

    @Column(name = "waktu_meninggal")
    private String waktu_meninggal;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "hari_pemakaman")
    private String hari_pemakaman;

    @Column(name = "tgl_pemakaman")
    private Date tgl_pemakaman;

    @Column(name = "waktu_pemakaman")
    private String waktu_pemakaman;

    @Column(name = "alamat_pemakaman")
    private String alamat_pemakaman;


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

    public Integer getUsia() {
        return usia;
    }

    public void setUsia(Integer usia) {
        this.usia = usia;
    }

    public String getHari_meninggal() {
        return hari_meninggal;
    }

    public void setHari_meninggal(String hari_meninggal) {
        this.hari_meninggal = hari_meninggal;
    }

    public Date getTgl_meninggal() {
        return tgl_meninggal;
    }

    public void setTgl_meninggal(Date tgl_meninggal) {
        this.tgl_meninggal = tgl_meninggal;
    }

    public String getWaktu_meninggal() {
        return waktu_meninggal;
    }

    public void setWaktu_meninggal(String waktu_meninggal) {
        this.waktu_meninggal = waktu_meninggal;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getHari_pemakaman() {
        return hari_pemakaman;
    }

    public void setHari_pemakaman(String hari_pemakaman) {
        this.hari_pemakaman = hari_pemakaman;
    }

    public Date getTgl_pemakaman() {
        return tgl_pemakaman;
    }

    public void setTgl_pemakaman(Date tgl_pemakaman) {
        this.tgl_pemakaman = tgl_pemakaman;
    }

    public String getWaktu_pemakaman() {
        return waktu_pemakaman;
    }

    public void setWaktu_pemakaman(String waktu_pemakaman) {
        this.waktu_pemakaman = waktu_pemakaman;
    }

    public String getAlamat_pemakaman() {
        return alamat_pemakaman;
    }

    public void setAlamat_pemakaman(String alamat_pemakaman) {
        this.alamat_pemakaman = alamat_pemakaman;
    }
}
