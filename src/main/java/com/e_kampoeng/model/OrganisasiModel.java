package com.e_kampoeng.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "organisasi")
public class OrganisasiModel {

    //    MAKE TABLE STATUS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jenis_surat;

    private Date create_at;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "organisasis")
//    private Set<WargaModel> wargas = new HashSet<>();

    //    getter setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJenis_surat() {
        return jenis_surat;
    }

    public void setJenis_surat(String jenis_surat) {
        this.jenis_surat = jenis_surat;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

//    public Set<WargaModel> getWargas() {
//        return wargas;
//    }
//
//    public void setWargas(Set<WargaModel> wargas) {
//        this.wargas = wargas;
//    }
}
