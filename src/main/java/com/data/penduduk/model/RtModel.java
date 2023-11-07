package com.data.penduduk.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rt")
public class RtModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_rt")
    private String nama_rt;

    @Column(name = "warga_id")
    private Integer warga_id;

    @CreationTimestamp
    @Column(name = "create_at ")
    private Date create_at;

    public RtModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama_rt() {
        return nama_rt;
    }

    public void setNama_rt(String nama_rt) {
        this.nama_rt = nama_rt;
    }

    public Integer getWarga_id() {
        return warga_id;
    }

    public void setWarga_id(Integer warga_id) {
        this.warga_id = warga_id;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }
}
