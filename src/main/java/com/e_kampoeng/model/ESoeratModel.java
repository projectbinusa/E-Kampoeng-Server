package com.e_kampoeng.model;

import com.e_kampoeng.config.DateConfig;
import com.e_kampoeng.enumed.JenisBantuanType;
import com.e_kampoeng.enumed.JenisSuratType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "e_soerat")
public class ESoeratModel extends DateConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jenis_surat")
    private String jenisSurat;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    @Column(name = "waktu_pengajuan")
    private Date waktuPengajuan;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    @Column(name = "waktu_di_setujui")
    private Date waktuDiSetujui;

    @ManyToOne
    @JoinColumn(name = "id_warga")
    private WargaModel warga;

    @Column(name = "status")
    private String status;

    @Column(name = "creator_email")
    private String creatorEmail;

    @Lob
    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "wilayah_rt_id")
    private WilayahRTModel wilayahRT;

    public ESoeratModel() {
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJenisSurat() {
        return jenisSurat;
    }

    public void setJenisSurat(String jenisSurat) {
        this.jenisSurat = jenisSurat;
    }

    public Date getWaktuPengajuan() {
        return waktuPengajuan;
    }

    public void setWaktuPengajuan(Date waktuPengajuan) {
        this.waktuPengajuan = waktuPengajuan;
    }

    public Date getWaktuDiSetujui() {
        return waktuDiSetujui;
    }

    public void setWaktuDiSetujui(Date waktuDiSetujui) {
        this.waktuDiSetujui = waktuDiSetujui;
    }

    public WargaModel getWarga() {
        return warga;
    }

    public void setWarga(WargaModel warga) {
        this.warga = warga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatorEmail() {
        return creatorEmail;
    }

    public void setCreatorEmail(String creatorEmail) {
        this.creatorEmail = creatorEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public WilayahRTModel getWilayahRT() {
        return wilayahRT;
    }

    public void setWilayahRT(WilayahRTModel wilayahRT) {
        this.wilayahRT = wilayahRT;
    }
}
