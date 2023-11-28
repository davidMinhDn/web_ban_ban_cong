package com.example.web_ban_cong.repository;

import com.example.web_ban_cong.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCombos_Id(Long comboId);
}
