package com.example.web_ban_cong.repository;

import com.example.web_ban_cong.model.ComboProduct;
import com.example.web_ban_cong.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComboProductRepository extends JpaRepository<ComboProduct, Long> {
    List<ComboProduct> findByComboId(Long comboId);
    void deleteByProductId(Long id);
}
