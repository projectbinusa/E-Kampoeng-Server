//package com.e_kampoeng.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.hibernate.annotations.CreationTimestamp;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@Table(name = "rw")
//public class RWModel {
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @CreationTimestamp
//    @Column(name = "create_at ")
//    private Date create_at;
//
////    @OneToOne
////    @PrimaryKeyJoinColumn(name = "warga_id")
////    private WargaModel warga;
//
//    //    @JsonIgnore
//    @OneToMany(mappedBy = "rw", cascade = CascadeType.ALL)
//    private WilayahRWModel wilRW;
//
//    @JsonIgnore
//    @OneToOne(fetch = FetchType.LAZY)
//    @MapsId
//    @JoinColumn(name = "warga_id", referencedColumnName = "id")
//    private WargaModel warga;
//
//    public RWModel() {
//    }
//
//    public RWModel(Long id, Date create_at, WargaModel warga, WilayahRWModel wilRW) {
//        this.id = id;
//        this.create_at = create_at;
//        this.warga = warga;
//        this.wilRW = wilRW;
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
//    public Date getCreate_at() {
//        return create_at;
//    }
//
//    public void setCreate_at(Date create_at) {
//        this.create_at = create_at;
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
//    public WilayahRWModel getWilRW() {
//        return wilRW;
//    }
//
//    public void setWilRW(WilayahRWModel wilRW) {
//        this.wilRW = wilRW;
//    }
//}

package com.e_kampoeng.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "rw")
public class RWModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "create_at ")
    private Date create_at;

    @OneToMany(mappedBy = "rw")
    private List<WilayahRWModel> wilRW;

    @ManyToOne
    @JoinColumn(name = "warga_id")
    private WargaModel warga;

    public RWModel() {
    }

    public RWModel(Long id, Date create_at, WargaModel warga, List<WilayahRWModel> wilayahRW) {
        this.id = id;
        this.create_at = create_at;
        this.warga = warga;
        this.wilRW = wilayahRW;
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

    public List<WilayahRWModel> getWilRW() {
        return wilRW;
    }

    public void setWilRW(List<WilayahRWModel> wilRW) {
        this.wilRW = wilRW;
    }
}