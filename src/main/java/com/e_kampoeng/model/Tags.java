package com.e_kampoeng.model;

import com.e_kampoeng.config.DateConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tags extends DateConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tags;

    @ManyToOne
    @JoinColumn(name = "wilayah_rt_id")
    private WilayahRTModel wilayahRT;


    @JsonIgnore
    @ManyToMany(mappedBy = "tagsBerita", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, targetEntity = Berita.class)
    private Set<Berita> beritas = new HashSet<>();

//    @ManyToOne
//    private Berita berita;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Set<Berita> getBeritas() {
        return beritas;
    }

    public void setBeritas(Set<Berita> beritas) {
        this.beritas = beritas;
    }

    public WilayahRTModel getWilayahRT() {
        return wilayahRT;
    }

    public void setWilayahRT(WilayahRTModel wilayahRT) {
        this.wilayahRT = wilayahRT;
    }
}
