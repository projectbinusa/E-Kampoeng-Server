package com.e_kampoeng.model;

import javax.persistence.*;

@Entity
@Table(name = "warga_organisasi")
public class OrganisasiWargaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_warga")
    private WargaModel warga;

    @ManyToOne
    @JoinColumn(name = "id_organisasi")
    private OrganisasiModel organisasi;

    public OrganisasiWargaModel() {
    }

    public OrganisasiWargaModel(Long id, WargaModel warga, OrganisasiModel organisasi) {
        this.id = id;
        this.warga = warga;
        this.organisasi = organisasi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WargaModel getWarga() {
        return warga;
    }

    public void setWarga(WargaModel warga) {
        this.warga = warga;
    }

    public OrganisasiModel getOrganisasi() {
        return organisasi;
    }

    public void setOrganisasi(OrganisasiModel organisasi) {
        this.organisasi = organisasi;
    }
}
