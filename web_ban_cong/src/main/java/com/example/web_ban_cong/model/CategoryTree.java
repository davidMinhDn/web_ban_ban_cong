package com.example.web_ban_cong.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "category_tree")
public class CategoryTree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_tree_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category_tree")
    private List<Tree> trees;
}
