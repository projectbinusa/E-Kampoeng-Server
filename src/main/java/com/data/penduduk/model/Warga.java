package com.data.penduduk.model;

import com.data.penduduk.enumated.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "warga")
public class Warga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private Long id;

    private String nama_lengkap;

    private String tmp_lahir;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date tgl_lahir;

    @Enumerated(EnumType.STRING)
    private GenderEnum jenis_kelamin;

    @Enumerated(EnumType.STRING)
    private ReligionEnum agama;

    private Integer nik;

    private Integer no_kk;

    @Enumerated(EnumType.STRING)
    private StatusInFamilyEnum status_in_family;

    @Enumerated(EnumType.STRING)
    private StatusPendudukEnum status_penduduk;

    private String pendidikan;

    private String pekerjaan;

    private Integer rt_id;

    private String status_kawin;

    @Enumerated(EnumType.STRING)
    private GolDarahEnum gol_darah;

    private String cacat;

    private String jenis_asuransi;

    private String jenis_kb;

    private String kelayakan_tmp;

    private String sumber_air;

    private Long wil_rt_id;

    private Date create_at;

    @OneToOne(mappedBy = "warga", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private KkModel kkModel;

    @ManyToMany
    @JoinTable(
            name = "warga_organisasi",
            joinColumns = @JoinColumn(name = "warga_id"),
            inverseJoinColumns = @JoinColumn(name = "organisasi_id")
    )
    private Set<Organisasi> organisasis = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getTmp_lahir() {
        return tmp_lahir;
    }

    public void setTmp_lahir(String tmp_lahir) {
        this.tmp_lahir = tmp_lahir;
    }

    public Date getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(Date tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public GenderEnum getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(GenderEnum jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public ReligionEnum getAgama() {
        return agama;
    }

    public void setAgama(ReligionEnum agama) {
        this.agama = agama;
    }

    public Integer getNik() {
        return nik;
    }

    public void setNik(Integer nik) {
        this.nik = nik;
    }

    public Integer getNo_kk() {
        return no_kk;
    }

    public void setNo_kk(Integer no_kk) {
        this.no_kk = no_kk;
    }

    public StatusInFamilyEnum getStatus_in_family() {
        return status_in_family;
    }

    public void setStatus_in_family(StatusInFamilyEnum status_in_family) {
        this.status_in_family = status_in_family;
    }

    public StatusPendudukEnum getStatus_penduduk() {
        return status_penduduk;
    }

    public void setStatus_penduduk(StatusPendudukEnum status_penduduk) {
        this.status_penduduk = status_penduduk;
    }

    public String getPendidikan() {
        return pendidikan;
    }

    public void setPendidikan(String pendidikan) {
        this.pendidikan = pendidikan;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public Integer getRt_id() {
        return rt_id;
    }

    public void setRt_id(Integer rt_id) {
        this.rt_id = rt_id;
    }

    public String getStatus_kawin() {
        return status_kawin;
    }

    public void setStatus_kawin(String status_kawin) {
        this.status_kawin = status_kawin;
    }

    public GolDarahEnum getGol_darah() {
        return gol_darah;
    }

    public void setGol_darah(GolDarahEnum gol_darah) {
        this.gol_darah = gol_darah;
    }

    public String getCacat() {
        return cacat;
    }

    public void setCacat(String cacat) {
        this.cacat = cacat;
    }

    public String getJenis_asuransi() {
        return jenis_asuransi;
    }

    public void setJenis_asuransi(String jenis_asuransi) {
        this.jenis_asuransi = jenis_asuransi;
    }

    public String getJenis_kb() {
        return jenis_kb;
    }

    public void setJenis_kb(String jenis_kb) {
        this.jenis_kb = jenis_kb;
    }

    public String getKelayakan_tmp() {
        return kelayakan_tmp;
    }

    public void setKelayakan_tmp(String kelayakan_tmp) {
        this.kelayakan_tmp = kelayakan_tmp;
    }

    public String getSumber_air() {
        return sumber_air;
    }

    public void setSumber_air(String sumber_air) {
        this.sumber_air = sumber_air;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Set<Organisasi> getOrganisasis() {
        return organisasis;
    }

    public void setOrganisasis(Set<Organisasi> organisasis) {
        this.organisasis = organisasis;
    }

    public KkModel getKkModel() {
        return kkModel;
    }

    public void setKkModel(KkModel kkModel) {
        this.kkModel = kkModel;
    }

    public Long getWil_rt_id() {
        return wil_rt_id;
    }

    public void setWil_rt_id(Long wil_rt_id) {
        this.wil_rt_id = wil_rt_id;
    }
}
