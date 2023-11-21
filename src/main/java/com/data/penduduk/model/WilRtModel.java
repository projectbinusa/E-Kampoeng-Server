package com.data.penduduk.model;

import javax.persistence.*;

@Entity
@Table(name = "wilayah_rt")
public class WilRtModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column
//    private Long rt_id;

//    @OneToOne(mappedBy = "wilRtModel", cascade = CascadeType.ALL)
//    private RtModel rtModel;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rt_id")
    private RtModel rtModel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RtModel getRtModel() {
        return rtModel;
    }

    public void setRtModel(RtModel rtModel) {
        this.rtModel = rtModel;
    }
}
