//package com.e_kampoeng.model;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "wilayah_rw")
//public class WilayahRWModel {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "nomor_rw")
//    private Long nomor_rw;
//
//    @Column(name = "nama_dusun")
//    private String nama_dusun;
//
//    @OneToMany(mappedBy = "wilRW", cascade = CascadeType.ALL)
//    private List<WilayahRTModel> wilRT;
//
//    @ManyToOne
//    @JoinColumn(name = "rw_id")
//    private RWModel wilRW;
//
//    public WilayahRWModel() {
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
//    public Long getNomor_rw() {
//        return nomor_rw;
//    }
//
//    public void setNomor_rw(Long nomor_rw) {
//        this.nomor_rw = nomor_rw;
//    }
//
//    public String getNama_dusun() {
//        return nama_dusun;
//    }
//
//    public void setNama_dusun(String nama_dusun) {
//        this.nama_dusun = nama_dusun;
//    }
//
//    public List<WilayahRTModel> getWilRT() {
//        return wilRT;
//    }
//
//    public void setWilRT(List<WilayahRTModel> wilRT) {
//        this.wilRT = wilRT;
//    }
//
//    public RWModel getWilRW() {
//        return wilRW;
//    }
//
//    public void setWilRW(RWModel wilRW) {
//        this.wilRW = wilRW;
//    }
//}




package com.e_kampoeng.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wilayah_rw")
public class WilayahRWModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomor_rw")
    private Long nomor_rw;

    @Column(name = "nama_dusun")
    private String nama_dusun;

    @ManyToOne
    @JoinColumn(name = "rw_id")
    private RWModel rw;

    public WilayahRWModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNomor_rw() {
        return nomor_rw;
    }

    public void setNomor_rw(Long nomor_rw) {
        this.nomor_rw = nomor_rw;
    }

    public String getNama_dusun() {
        return nama_dusun;
    }

    public void setNama_dusun(String nama_dusun) {
        this.nama_dusun = nama_dusun;
    }

    public RWModel getRw() {
        return rw;
    }

    public void setRw(RWModel rw) {
        this.rw = rw;
    }
}