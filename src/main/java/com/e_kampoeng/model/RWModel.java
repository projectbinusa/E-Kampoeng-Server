package com.e_kampoeng.model;

import com.e_kampoeng.config.DateConfig;

import javax.persistence.*;

@Table(name = "rw")
@Entity
public class RWModel extends DateConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "wilayah_rw_id")
    private WilayahRWModel wilayahRW;

    @OneToOne
    @JoinColumn(name = "warga_id")
    private WargaModel warga;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WilayahRWModel getWilayahRW() {
        return wilayahRW;
    }

    public void setWilayahRW(WilayahRWModel wilayahRW) {
        this.wilayahRW = wilayahRW;
    }

    public WargaModel getWarga() {
        return warga;
    }

    public void setWarga(WargaModel warga) {
        this.warga = warga;
    }
}
