package com.e_kampoeng.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "rt")
public class RTModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomor_rt")
    private Long nomorRT;

    @ManyToOne
    @JoinColumn(name = "wilayah_rt_id")
    private WilayahRTModel wilayahRT;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "warga_id")
    private WargaModel warga;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNomorRT() {
        return nomorRT;
    }

    public void setNomorRT(Long nomorRT) {
        this.nomorRT = nomorRT;
    }

    public WilayahRTModel getWilayahRT() {
        return wilayahRT;
    }

    public void setWilayahRT(WilayahRTModel wilayahRT) {
        this.wilayahRT = wilayahRT;
    }

    public WargaModel getWarga() {
        return warga;
    }

    public void setWarga(WargaModel warga) {
        this.warga = warga;
    }
}
