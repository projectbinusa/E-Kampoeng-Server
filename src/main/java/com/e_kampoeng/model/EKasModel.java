//package com.e_kampoeng.model;
//
//import com.e_kampoeng.config.DateConfig;
//import com.fasterxml.jackson.annotation.JsonFormat;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@Table(name = "e_kas")
//public class EKasModel extends DateConfig {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "pemasukan")
//    private Long pemasukan;
//
//    @Column(name = "pengeluaran")
//    private Long pengeluaran;
//
//    @Column(name = "ket_pemasukan")
//    private String ket_pemasukan;
//
//    @Column(name = "ket_pengeluaran")
//    private String ket_pengeluaran;
//
//    @ManyToOne
//    @JoinColumn(name = "wilayah_rt_id")
//    private WilayahRTModel wilayahRT;
//
//    @JsonFormat(pattern = "dd-MM-yyyy")
//    private Date waktu;
//
//
//    public EKasModel() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getPemasukan() {
//        return pemasukan;
//    }
//
//    public void setPemasukan(Long pemasukan) {
//        this.pemasukan = pemasukan;
//    }
//
//    public Long getPengeluaran() {
//        return pengeluaran;
//    }
//
//    public void setPengeluaran(Long pengeluaran) {
//        this.pengeluaran = pengeluaran;
//    }
//
//    public String getKet_pemasukan() {
//        return ket_pemasukan;
//    }
//
//    public void setKet_pemasukan(String ket_pemasukan) {
//        this.ket_pemasukan = ket_pemasukan;
//    }
//
//    public String getKet_pengeluaran() {
//        return ket_pengeluaran;
//    }
//
//    public void setKet_pengeluaran(String ket_pengeluaran) {
//        this.ket_pengeluaran = ket_pengeluaran;
//    }
//
//    public WilayahRTModel getWilayahRT() {
//        return wilayahRT;
//    }
//
//    public void setWilayahRT(WilayahRTModel wilayahRT) {
//        this.wilayahRT = wilayahRT;
//    }
//
//    public Date getWaktu() {
//        return waktu;
//    }
//
//    public void setWaktu(Date waktu) {
//        this.waktu = waktu;
//    }
//}
