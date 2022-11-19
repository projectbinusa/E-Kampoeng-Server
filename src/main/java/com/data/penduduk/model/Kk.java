package com.data.penduduk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.lang.String;
import java.util.List;

@Entity
@Table(name = "kk")
public class Kk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama")
    private String nama;

    @Column(name = "tempat_lahir")
    private String tempat_lahir;

    @Column(name = "tgl_lahir")
    private String tgl_lahir;


    @Column(name = "gender")
    private String gender;

    @Column(name = "agama")
    private String agama;

    @Column(name = "status")
    private String status;

    @JsonIgnore
    @ManyToOne
    private Rt rt;

    @OneToMany(mappedBy = "kk", cascade = CascadeType.REMOVE)
    private List<Warga> warga;

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

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Rt getRt() {
        return rt;
    }

    public void setRt(Rt rt) {
        this.rt = rt;
    }

    public List<Warga> getWarga() {
        return warga;
    }

    public void setWarga(List<Warga> warga) {
        this.warga = warga;
    }
}
