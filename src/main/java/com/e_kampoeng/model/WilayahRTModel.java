package com.e_kampoeng.model;

import com.e_kampoeng.model.WilayahRWModel;

import javax.persistence.*;

@Entity
public class WilayahRTModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long nomorRt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wilayah_rw_id")
    private WilayahRWModel wilayahRW;

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

    public WilayahRWModel getWilayahRW() {
        return wilayahRW;
    }

    public void setWilayahRW(WilayahRWModel wilayahRW) {
        this.wilayahRW = wilayahRW;
    }
}
