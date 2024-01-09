package com.example.web_ban_cong.repository;

import com.example.web_ban_cong.model.Product;
import com.example.web_ban_cong.model.WishlistProduct;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListProductRepository extends JpaRepository<WishlistProduct, Long> {
    public List<WishlistProduct> findByUserId(Long id);
    @Transactional
    public void deleteByProduct (Product product);
}
