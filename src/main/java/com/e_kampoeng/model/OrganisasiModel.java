package com.e_kampoeng.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "organisasi")
public class OrganisasiModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nama_organisasi;

    @Column
    private Date create_at;

    @OneToMany(mappedBy = "organisasi", cascade = CascadeType.ALL)
    private List<OrganisasiWargaModel> wargas = new ArrayList<>();

    public OrganisasiModel() {
    }

    public OrganisasiModel(Long id, String nama_organisasi, Date create_at, List<OrganisasiWargaModel> wargas) {
        this.id = id;
        this.nama_organisasi = nama_organisasi;
        this.create_at = create_at;
        this.wargas = wargas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama_organisasi() {
        return nama_organisasi;
    }

    public void setNama_organisasi(String nama_organisasi) {
        this.nama_organisasi = nama_organisasi;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public List<OrganisasiWargaModel> getWargas() {
        return wargas;
    }

    public void setWargas(List<OrganisasiWargaModel> wargas) {
        this.wargas = wargas;
    }
}
