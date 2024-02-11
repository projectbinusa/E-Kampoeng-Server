package com.e_kampoeng.model;

import com.e_kampoeng.dto.Warga;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rt")
public class RTModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "create_at ")
    private Date create_at;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "warga_id")
    private WargaModel warga;

    @JsonIgnore
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private WilayahRTModel wilayahRT;


    public RTModel() {
    }

    public RTModel(Long id, Date create_at, WargaModel warga, WilayahRTModel wilayahRT) {
        this.id = id;
        this.create_at = create_at;
        this.warga = warga;
        this.wilayahRT = wilayahRT;
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

    public WilayahRTModel getWilayahRT() {
        return wilayahRT;
    }

    public void setWilayahRT(WilayahRTModel wilayahRT) {
        this.wilayahRT = wilayahRT;
    }
}
