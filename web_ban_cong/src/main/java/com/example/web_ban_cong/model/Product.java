package com.example.web_ban_cong.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.CustomLog;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "title")
    private String title;

    @Column(name = "size")
    private String size;

    @Column(name = "manufacturing_date")
    private String manufacturing_date;

    @Column(name = "cach_bao_quan")
    private String cach_bao_quan;

    @Column(name = "shape")
    private String shape;

    @Column(name = "chat_lieu")
    private String chat_lieu;

    @Column(name = "dac_tinh")
    private String dac_tinh;

    @Column(name = "image")
    private String image;

    @Column(name = "price_other")
    private double priceOther;

    @Column(name = "refund")
    private boolean refund;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImages;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "products")
    private List<Combo> combos = new ArrayList<>();

}
