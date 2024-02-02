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

    @ManyToOne
    @JoinColumn(name = "wil_rw_id")
    private WilayahRWModel wilRW;

    @ManyToOne
    @JoinColumn(name = "rt_id")
    private RTModel rt;

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

    public WilayahRWModel getWilRW() {
        return wilRW;
    }

    public void setWilRW(WilayahRWModel wilRW) {
        this.wilRW = wilRW;
    }

    public RTModel getRt() {
        return rt;
    }

    public void setRt(RTModel rt) {
        this.rt = rt;
    }
}
