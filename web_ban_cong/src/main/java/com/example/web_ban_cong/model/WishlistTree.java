package com.example.web_ban_cong.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wishlist_tree")
@Builder
public class WishlistTree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tree_id")
    private Tree tree;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
