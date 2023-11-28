package com.example.web_ban_cong.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "flowers_and_trees")
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tree_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "size")
    private String size;

    @Column(name = "type")
    private int type;

    @Column(name = "flower_color")
    private String flowerColor;

    @Column(name = "most_season")
    private String mostSeason;

    @Column(name = "image")
    private String image;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "description")
    private String description;

    @Column(name = "how_ to_care")
    private String howToCare;

    @Column(name = "type_detail")
    private String typeDetail;

    @Column(name = "title")
    private String title;

    @ManyToMany(mappedBy = "flowers_and_trees")
    private List<Combo> combos = new ArrayList<>();
}
