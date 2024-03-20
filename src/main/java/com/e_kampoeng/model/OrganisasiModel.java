package com.e_kampoeng.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "organisasi")
public class OrganisasiModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nama_organisasi;


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

}
