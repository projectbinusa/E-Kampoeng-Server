package com.e_kampoeng.model;



import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "wilRW", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WilayahRTModel> rtModels;




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

    public List<WilayahRTModel> getRtModels() {
        return rtModels;
    }

    public void setRtModels(List<WilayahRTModel> rtModels) {
        this.rtModels = rtModels;
    }
}