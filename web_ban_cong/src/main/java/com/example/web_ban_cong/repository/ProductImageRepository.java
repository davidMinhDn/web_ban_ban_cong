package com.example.web_ban_cong.repository;

import com.example.web_ban_cong.model.Product;
import com.example.web_ban_cong.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    List<ProductImage> findByProductId(Long product_id);
}
