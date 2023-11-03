package com.data.penduduk.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "e_seorat")
public class ESoeratModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jenis_surat")
    private String jenis_surat;

    @Column(name = "jenis_bantuan")
    private String jenis_bantuan;

    @CreationTimestamp
    @Column(name = "create_at ")
    private Date create_at;

    public ESoeratModel() {
    }

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

    public String getJenis_bantuan() {
        return jenis_bantuan;
    }

    public void setJenis_bantuan(String jenis_bantuan) {
        this.jenis_bantuan = jenis_bantuan;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }
}
