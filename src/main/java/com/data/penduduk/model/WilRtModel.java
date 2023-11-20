package com.data.penduduk.model;

import javax.persistence.*;

@Entity
@Table(name = "wilayah_rt")
public class WilRtModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long rt_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRt_id() {
        return rt_id;
    }

    public void setRt_id(Long rt_id) {
        this.rt_id = rt_id;
    }
}
