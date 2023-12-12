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

    @Column
    private Integer nomor_rw;

    @Column
    private Integer nama_dusun;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "id_rw")
    private RWModel rw;

    @OneToMany(mappedBy = "wilayah_rw", cascade = CascadeType.ALL)
    private List<WargaModel> wargas = new ArrayList<>();

    public WilayahRWModel() {
    }

    public WilayahRWModel(Long id, Integer nomor_rw, Integer nama_dusun, RWModel rw, List<WargaModel> wargas) {
        this.id = id;
        this.nomor_rw = nomor_rw;
        this.nama_dusun = nama_dusun;
        this.rw = rw;
        this.wargas = wargas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNomor_rw() {
        return nomor_rw;
    }

    public void setNomor_rw(Integer nomor_rw) {
        this.nomor_rw = nomor_rw;
    }

    public Integer getNama_dusun() {
        return nama_dusun;
    }

    public void setNama_dusun(Integer nama_dusun) {
        this.nama_dusun = nama_dusun;
    }

    public RWModel getRw() {
        return rw;
    }

    public void setRw(RWModel rw) {
        this.rw = rw;
    }

    public List<WargaModel> getWargas() {
        return wargas;
    }

    public void setWargas(List<WargaModel> wargas) {
        this.wargas = wargas;
    }
}