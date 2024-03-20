package com.e_kampoeng.model;

import javax.persistence.*;

@Entity
public class WilayahRWModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String namaDusun;
    private Long nomorRw;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaDusun() {
        return namaDusun;
    }

    public void setNamaDusun(String namaDusun) {
        this.namaDusun = namaDusun;
    }

    public Long getNomorRw() {
        return nomorRw;
    }

    public void setNomorRw(Long nomorRw) {
        this.nomorRw = nomorRw;
    }
}