package com.data.penduduk.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "undangan_warga")
public class UndanganWarga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "perihal")
    private String perihal;

    @Column(name = "hari")
    private String hari;

    @Column(name = "tanggal")
    private Date tanggal;

    @Column(name = "waktu")
    private String waktu;

    @Column(name = "tempat")
    private String tempat;

    @Column(name = "acara")
    private String acara;

    @Column(name = "ditujukan")
    private String ditujukan;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getAcara() {
        return acara;
    }

    public void setAcara(String acara) {
        this.acara = acara;
    }

    public String getDitujukan() {
        return ditujukan;
    }

    public void setDitujukan(String ditujukan) {
        this.ditujukan = ditujukan;
    }
}
