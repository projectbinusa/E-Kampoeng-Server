package com.data.penduduk.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "warga_organisasi")
public class WargaOrganisasi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne
    @JoinColumn(name = "warga_id")
    private Warga warga;

    @ManyToOne
    @JoinColumn(name = "organisasi_id")
    private Organisasi organisasi;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Warga getWarga_id() {
        return warga;
    }

    public void setWarga_id(Warga warga_id) {
        this.warga = warga_id;
    }

    public Organisasi getOrganisasi_id() {
        return organisasi;
    }

    public void setOrganisasi_id(Organisasi organisasi_id) {
        this.organisasi = organisasi_id;
    }
}
