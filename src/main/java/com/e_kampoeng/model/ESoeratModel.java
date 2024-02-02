package com.e_kampoeng.model;

import com.e_kampoeng.enumed.JenisBantuanType;
import com.e_kampoeng.enumed.JenisSuratType;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "e_soerat")
public class ESoeratModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "jenis_surat")
    private JenisSuratType jenis_surat;

    @Enumerated(EnumType.STRING)
    @Column(name = "jenis_bantuan")
    private JenisBantuanType jenis_bantuan;

    @CreationTimestamp
    @Column(name = "create_at")
    private Date create_at;


    public ESoeratModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JenisSuratType getJenis_surat() {
        return jenis_surat;
    }

    public void setJenis_surat(JenisSuratType jenis_surat) {
        this.jenis_surat = jenis_surat;
    }

    public JenisBantuanType getJenis_bantuan() {
        return jenis_bantuan;
    }

    public void setJenis_bantuan(JenisBantuanType jenis_bantuan) {
        this.jenis_bantuan = jenis_bantuan;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }
}