package com.data.penduduk.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "agenda_warga")
public class AgendaWarga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_kegiatan")
    private String nama_kegiatan;

    @Column(name = "deskripsi")
    private String deskripsi;

    @Column(name = "tgl_dilaksanakan")
    private Date tgl_dilaksanakan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama_kegiatan() {
        return nama_kegiatan;
    }

    public void setNama_kegiatan(String nama_kegiatan) {
        this.nama_kegiatan = nama_kegiatan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Date getTgl_dilaksanakan() {
        return tgl_dilaksanakan;
    }

    public void setTgl_dilaksanakan(Date tgl_dilaksanakan) {
        this.tgl_dilaksanakan = tgl_dilaksanakan;
    }
}