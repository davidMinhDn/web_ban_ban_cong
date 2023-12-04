package com.example.web_ban_cong.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "combo_image")
public class ComboImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "combo_image_id")
    private Long id;

    @Column(name = "image_url")
    private String image;

    @ManyToOne
    @JoinColumn(name = "combo_id")
    @JsonIgnore
    private Combo combo;
}
