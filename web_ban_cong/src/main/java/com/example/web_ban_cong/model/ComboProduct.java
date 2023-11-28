package com.example.web_ban_cong.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "combo_product")
public class ComboProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "combo_product_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "combo_id")
    private Combo combo;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
