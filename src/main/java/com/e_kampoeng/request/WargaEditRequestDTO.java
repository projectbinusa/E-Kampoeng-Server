package com.e_kampoeng.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class WargaEditRequestDTO {
    private String tempatLahir;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date tanggalLahir;
    private String jenisKelamin;
    private String agama;
    private String noKk;
    private String statusDalamKeluarga;
    private String statusKependudukan;
    private Long noAnak;
    private Double panjangLahir;
    private Double beratLahir;
    private Long noPassport;
    private String namaAyah;
    private String namaIbu;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date tanggalPerkawinan;
    private String alamatSebelumnya;
    private Long noBpjs;
    private String pendidikanTempuh;
    private String pendidikanTerakhir;
    private String statusPerkawinan;
    private String golonganDarah;
    private String jenisAsuransi;
    private String jenisKb;
    private String kesesuaianTempat;
    private String sumberAir;

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getNoKk() {
        return noKk;
    }

    public void setNoKk(String noKk) {
        this.noKk = noKk;
    }

    public String getStatusDalamKeluarga() {
        return statusDalamKeluarga;
    }

    public void setStatusDalamKeluarga(String statusDalamKeluarga) {
        this.statusDalamKeluarga = statusDalamKeluarga;
    }

    public String getStatusKependudukan() {
        return statusKependudukan;
    }

    public void setStatusKependudukan(String statusKependudukan) {
        this.statusKependudukan = statusKependudukan;
    }

    public Long getNoAnak() {
        return noAnak;
    }

    public void setNoAnak(Long noAnak) {
        this.noAnak = noAnak;
    }

    public Double getPanjangLahir() {
        return panjangLahir;
    }

    public void setPanjangLahir(Double panjangLahir) {
        this.panjangLahir = panjangLahir;
    }

    public Double getBeratLahir() {
        return beratLahir;
    }

    public void setBeratLahir(Double beratLahir) {
        this.beratLahir = beratLahir;
    }

    public Long getNoPassport() {
        return noPassport;
    }

    public void setNoPassport(Long noPassport) {
        this.noPassport = noPassport;
    }

    public String getNamaAyah() {
        return namaAyah;
    }

    public void setNamaAyah(String namaAyah) {
        this.namaAyah = namaAyah;
    }

    public String getNamaIbu() {
        return namaIbu;
    }

    public void setNamaIbu(String namaIbu) {
        this.namaIbu = namaIbu;
    }

    public Date getTanggalPerkawinan() {
        return tanggalPerkawinan;
    }

    public void setTanggalPerkawinan(Date tanggalPerkawinan) {
        this.tanggalPerkawinan = tanggalPerkawinan;
    }

    public String getAlamatSebelumnya() {
        return alamatSebelumnya;
    }

    public void setAlamatSebelumnya(String alamatSebelumnya) {
        this.alamatSebelumnya = alamatSebelumnya;
    }

    public Long getNoBpjs() {
        return noBpjs;
    }

    public void setNoBpjs(Long noBpjs) {
        this.noBpjs = noBpjs;
    }

    public String getPendidikanTempuh() {
        return pendidikanTempuh;
    }

    public void setPendidikanTempuh(String pendidikanTempuh) {
        this.pendidikanTempuh = pendidikanTempuh;
    }

    public String getPendidikanTerakhir() {
        return pendidikanTerakhir;
    }

    public void setPendidikanTerakhir(String pendidikanTerakhir) {
        this.pendidikanTerakhir = pendidikanTerakhir;
    }

    public String getStatusPerkawinan() {
        return statusPerkawinan;
    }

    public void setStatusPerkawinan(String statusPerkawinan) {
        this.statusPerkawinan = statusPerkawinan;
    }

    public String getGolonganDarah() {
        return golonganDarah;
    }

    public void setGolonganDarah(String golonganDarah) {
        this.golonganDarah = golonganDarah;
    }

    public String getJenisAsuransi() {
        return jenisAsuransi;
    }

    public void setJenisAsuransi(String jenisAsuransi) {
        this.jenisAsuransi = jenisAsuransi;
    }

    public String getJenisKb() {
        return jenisKb;
    }

    public void setJenisKb(String jenisKb) {
        this.jenisKb = jenisKb;
    }

    public String getKesesuaianTempat() {
        return kesesuaianTempat;
    }

    public void setKesesuaianTempat(String kesesuaianTempat) {
        this.kesesuaianTempat = kesesuaianTempat;
    }

    public String getSumberAir() {
        return sumberAir;
    }

    public void setSumberAir(String sumberAir) {
        this.sumberAir = sumberAir;
    }
}
