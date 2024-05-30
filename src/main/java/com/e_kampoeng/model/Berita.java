package com.e_kampoeng.model;

import com.e_kampoeng.config.DateConfig;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "berita")
public class Berita extends DateConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String judulBerita;
    private String author;
    @Lob
    private String isiBerita;
    @Lob
    private String image;

    @ManyToOne
    @JoinColumn(name = "wilayah_rt_id")
    private WilayahRTModel wilayahRT;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private CategoryBerita categoryBerita;

    public Berita() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJudulBerita() {
        return judulBerita;
    }

    public void setJudulBerita(String judulBerita) {
        this.judulBerita = judulBerita;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsiBerita() {
        return isiBerita;
    }

    public void setIsiBerita(String isiBerita) {
        this.isiBerita = isiBerita;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CategoryBerita getCategoryBerita() {
        return categoryBerita;
    }

    public void setCategoryBerita(CategoryBerita categoryBerita) {
        this.categoryBerita = categoryBerita;
    }

    public WilayahRTModel getWilayahRT() {
        return wilayahRT;
    }

    public void setWilayahRT(WilayahRTModel wilayahRT) {
        this.wilayahRT = wilayahRT;
    }
}
