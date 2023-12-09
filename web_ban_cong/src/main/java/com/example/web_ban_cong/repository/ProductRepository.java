package com.example.web_ban_cong.repository;

import com.example.web_ban_cong.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCombos_Id(Long comboId);
    List<Product> findByCategory_id(Long categoryId);
    @Query("SELECT p FROM Product p WHERE p.category.id != :categoryId OR p.category IS NULL")
    List<Product> findAllProductsNotRelated(@Param("categoryId") Long categoryId);

}
