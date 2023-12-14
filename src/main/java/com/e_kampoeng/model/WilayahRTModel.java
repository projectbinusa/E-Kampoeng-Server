package com.e_kampoeng.model;

import javax.persistence.*;

@Entity
@Table(name="wilayah_rt")
public class WilayahRTModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomor_rt")
    private Long nomor_rt;

    public WilayahRTModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNomor_rt() {
        return nomor_rt;
    }

    public void setNomor_rt(Long nomor_rt) {
        this.nomor_rt = nomor_rt;
    }
}
