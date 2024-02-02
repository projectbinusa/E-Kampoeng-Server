package com.e_kampoeng.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "rt")
public class RTModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "create_at ")
    private Date create_at;

    @OneToMany(mappedBy = "rt", cascade = CascadeType.ALL)
    private List<WilayahRTModel> wilRT;

    @ManyToOne
    @JoinColumn(name = "warga_id")
    private WargaModel warga;

    public RTModel() {
    }

    public RTModel(Long id, Date create_at, List<WilayahRTModel> wilRT, WargaModel warga) {
        this.id = id;
        this.create_at = create_at;
        this.wilRT = wilRT;
        this.warga = warga;
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

    public List<WilayahRTModel> getWilRT() {
        return wilRT;
    }

    public void setWilRT(List<WilayahRTModel> wilRT) {
        this.wilRT = wilRT;
    }

    public WargaModel getWarga() {
        return warga;
    }

    public void setWarga(WargaModel warga) {
        this.warga = warga;
    }
}
