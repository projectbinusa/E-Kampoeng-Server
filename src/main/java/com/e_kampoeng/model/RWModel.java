package com.e_kampoeng.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rw")
public class RWModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomor_rw")
    private Long nomorRW;

    @ManyToOne
    @JoinColumn(name = "wilayah_rw_id")
    private WilayahRWModel wilayahRW;

    @ManyToOne
    @JoinColumn(name = "warga_id")
    private WargaModel warga;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNomorRW() {
        return nomorRW;
    }

    public void setNomorRW(Long nomorRW) {
        this.nomorRW = nomorRW;
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
