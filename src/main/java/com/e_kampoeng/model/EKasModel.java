package com.e_kampoeng.model;

import com.e_kampoeng.config.DateConfig;

import javax.persistence.*;

@Entity
@Table(name = "e_kas")
public class EKasModel extends DateConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pemasukan")
    private Integer pemasukan;

    @Column(name = "pengeluaran")
    private Integer pengeluaran;

    @Column(name = "sisa_kas_bulan_lalu")
    private Integer sisa_kas_bulan_lalu;

    @Column(name = "sisa_kas_bulan_ini")
    private Integer sisa_kas_bulan_ini;

    @Column(name = "ket_pemasukan")
    private String ket_pemasukan;

    @Column(name = "ket_pengeluaran")
    private String ket_pengeluaran;

    public EKasModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPemasukan() {
        return pemasukan;
    }

    public void setPemasukan(Integer pemasukan) {
        this.pemasukan = pemasukan;
    }

    public Integer getPengeluaran() {
        return pengeluaran;
    }

    public void setPengeluaran(Integer pengeluaran) {
        this.pengeluaran = pengeluaran;
    }

    public Integer getSisa_kas_bulan_lalu() {
        return sisa_kas_bulan_lalu;
    }

    public void setSisa_kas_bulan_lalu(Integer sisa_kas_bulan_lalu) {
        this.sisa_kas_bulan_lalu = sisa_kas_bulan_lalu;
    }

    public Integer getSisa_kas_bulan_ini() {
        return sisa_kas_bulan_ini;
    }

    public void setSisa_kas_bulan_ini(Integer sisa_kas_bulan_ini) {
        this.sisa_kas_bulan_ini = sisa_kas_bulan_ini;
    }

    public String getKet_pemasukan() {
        return ket_pemasukan;
    }

    public void setKet_pemasukan(String ket_pemasukan) {
        this.ket_pemasukan = ket_pemasukan;
    }

    public String getKet_pengeluaran() {
        return ket_pengeluaran;
    }

    public void setKet_pengeluaran(String ket_pengeluaran) {
        this.ket_pengeluaran = ket_pengeluaran;
    }
}
