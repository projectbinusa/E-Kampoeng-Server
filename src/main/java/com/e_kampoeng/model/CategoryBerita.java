package com.e_kampoeng.model;

import com.e_kampoeng.config.DateConfig;

import javax.persistence.*;

@Entity
@Table(name = "category_berita")
public class CategoryBerita extends DateConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
