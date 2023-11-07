package com.data.penduduk.model;

import jdk.jfr.Timestamp;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "kk")
public class KkModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "warga_id")
    private Integer warga_id;

    @CreationTimestamp
    @Column(name = "create_at ")
    private Date create_at;

    public KkModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWarga_id() {
        return warga_id;
    }

    public void setWarga_id(Integer warga_id) {
        this.warga_id = warga_id;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }
}
