package com.e_kampoeng.model;

import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.model.WilayahRTModel;

import javax.persistence.*;

@Entity
public class RTWilayah {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "wilayah_rt_id")
    private WilayahRTModel wilayahRT;

    @OneToOne
    @JoinColumn(name = "warga_id")
    private WargaModel warga;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

