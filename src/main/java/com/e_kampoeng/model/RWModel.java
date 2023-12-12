package com.e_kampoeng.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rw")
public class RWModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "create_at ")
    private Date create_at;

//    @OneToOne
//    @PrimaryKeyJoinColumn(name = "warga_id")
//    private WargaModel warga;

    @JsonIgnore
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private WilayahRWModel wilayahRW;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "warga_id", referencedColumnName = "id")
    private WargaModel warga;

    public RWModel() {
    }

    public RWModel(Long id, Date create_at, WargaModel warga, WilayahRWModel wilayahRW) {
        this.id = id;
        this.create_at = create_at;
        this.warga = warga;
        this.wilayahRW = wilayahRW;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public WargaModel getWarga() {
        return warga;
    }

    public void setWarga(WargaModel warga) {
        this.warga = warga;
    }

    public WilayahRWModel getWilayahRW() {
        return wilayahRW;
    }

    public void setWilayahRW(WilayahRWModel wilayahRW) {
        this.wilayahRW = wilayahRW;
    }
}