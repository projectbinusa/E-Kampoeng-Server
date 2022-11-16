package com.data.penduduk.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "laporan_warga")
public class LaporanWarga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_pelapor")
    private String nama_pelapor;

    @Column(name = "laporan")
    private String laporan;

    @Column(name = "tgl_laporan")
    private Date tgl_laporan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama_pelapor() {
        return nama_pelapor;
    }

    public void setNama_pelapor(String nama_pelapor) {
        this.nama_pelapor = nama_pelapor;
    }

    public String getLaporan() {
        return laporan;
    }

    public void setLaporan(String laporan) {
        this.laporan = laporan;
    }

    public Date getTgl_laporan() {
        return tgl_laporan;
    }

    public void setTgl_laporan(Date tgl_laporan) {
        this.tgl_laporan = tgl_laporan;
    }
}
