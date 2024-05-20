package com.e_kampoeng.model;

import com.e_kampoeng.config.DateConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Table(name = "wilayah_rt")
@Entity
public class WilayahRTModel extends DateConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long nomorRt;
    @OneToOne
    @JoinColumn(name = "kepala_rt_id")
    private WargaModel kepalaRt;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNomorRt() {
        return nomorRt;
    }

    public void setNomorRt(Long nomorRt) {
        this.nomorRt = nomorRt;
    }

    public WargaModel getKepalaRt() {
        return kepalaRt;
    }

    public void setKepalaRt(WargaModel kepalaRt) {
        this.kepalaRt = kepalaRt;
    }
}
