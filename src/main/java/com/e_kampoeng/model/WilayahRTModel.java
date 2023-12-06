package com.e_kampoeng.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wilayah_rt")
public class WilayahRTModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer nomor_rt;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "id_rt")
    private RTModel rt;

    @OneToMany(mappedBy = "wilayah_rt", cascade = CascadeType.ALL)
    private List<WargaModel> wargas = new ArrayList<>();

    public WilayahRTModel() {
    }

    public WilayahRTModel(Long id, Integer nomor_rt, RTModel rt, List<WargaModel> wargas) {
        this.id = id;
        this.nomor_rt = nomor_rt;
        this.rt = rt;
        this.wargas = wargas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNomor_rt() {
        return nomor_rt;
    }

    public void setNomor_rt(Integer nomor_rt) {
        this.nomor_rt = nomor_rt;
    }

    public RTModel getRt() {
        return rt;
    }

    public void setRt(RTModel rt) {
        this.rt = rt;
    }

    public List<WargaModel> getWargas() {
        return wargas;
    }

    public void setWargas(List<WargaModel> wargas) {
        this.wargas = wargas;
    }
}
