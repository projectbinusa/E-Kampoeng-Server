package com.data.penduduk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "organisasi")
public class Organisasi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private Long id;

    private String jenis_surat;

    private Date create_at;

    @JsonIgnore
    @ManyToMany(mappedBy = "organisasis")
    private Set<Warga> wargas = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJenis_surat() {
        return jenis_surat;
    }

    public void setJenis_surat(String jenis_surat) {
        this.jenis_surat = jenis_surat;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Set<Warga> getWargas() {
        return wargas;
    }

    public void setWargas(Set<Warga> wargas) {
        this.wargas = wargas;
    }
}
