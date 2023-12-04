package com.e_kampoeng.model;

import javax.persistence.*;

@Entity
@Table(name = "rw")
public class RWModel {


    //    MAKE TABLE GURU
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    @Column
    private String nama;
    @Column
    private String tempat;
    @Column
    private String tanggal;
    @Column
    private String alamat;

    //    getter setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
