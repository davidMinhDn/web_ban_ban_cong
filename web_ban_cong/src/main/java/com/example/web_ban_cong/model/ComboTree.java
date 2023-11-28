package com.example.web_ban_cong.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comdo_tree_detail")
public class ComboTree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "combo_tree_id")
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "combo_id")
    private Combo combo;

    @ManyToOne
    @JoinColumn(name = "tree_id")
    private Tree tree;

}
