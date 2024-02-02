//package com.e_kampoeng.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.hibernate.annotations.CreationTimestamp;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@Table(name = "kk")
//public class KKModel {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @JsonIgnore
//    @OneToOne(fetch = FetchType.LAZY)
//    @MapsId
//    @JoinColumn(name = "id_warga", referencedColumnName = "id")
//    private WargaModel warga;
//
//    @CreationTimestamp
//    @Column(name = "create_at ")
//    private Date create_at;
//
//    public KKModel() {
//    }
//
//    public KKModel(Long id, WargaModel warga, Date create_at) {
//        this.id = id;
//        this.warga = warga;
//        this.create_at = create_at;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public WargaModel getWarga() {
//        return warga;
//    }
//
//    public void setWarga(WargaModel warga) {
//        this.warga = warga;
//    }
//
//    public Date getCreate_at() {
//        return create_at;
//    }
//
//    public void setCreate_at(Date create_at) {
//        this.create_at = create_at;
//    }
//}