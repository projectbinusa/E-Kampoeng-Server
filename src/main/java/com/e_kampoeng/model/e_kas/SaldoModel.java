package com.e_kampoeng.model.e_kas;


import com.e_kampoeng.config.DateConfig;
import com.e_kampoeng.model.WilayahRTModel;

import javax.persistence.*;

@Entity
@Table(name = "saldo")
public class SaldoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double jumlahSaldo;
    @ManyToOne
    @JoinColumn(name = "wilayah_rt_id")
    private WilayahRTModel wilayahRT;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getJumlahSaldo() {
        return jumlahSaldo;
    }

    public void setJumlahSaldo(Double jumlahSaldo) {
        this.jumlahSaldo = jumlahSaldo;
    }

    public WilayahRTModel getWilayahRT() {
        return wilayahRT;
    }

    public void setWilayahRT(WilayahRTModel wilayahRT) {
        this.wilayahRT = wilayahRT;
    }
}
