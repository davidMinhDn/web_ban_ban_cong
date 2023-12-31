package com.example.web_ban_cong.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank(message = "name cannot be blank")
    @Column(name = "user_name")
    private String userName;

    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dob")
    private Date dob;

    @NotBlank(message = "Email number is required")
    @Email(message = "Please provide a valid email address")
    @Column(name = "mail")
    private String mail;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @Column(name = "active")
    private boolean active;

    @ManyToMany
    @JoinTable(
            name = "wishlist_product",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "wishlist_tree",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tree_id")
    )
    private List<Tree> trees = new ArrayList<>();
}
