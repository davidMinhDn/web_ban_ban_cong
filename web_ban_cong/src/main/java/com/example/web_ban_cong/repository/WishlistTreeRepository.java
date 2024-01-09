package com.example.web_ban_cong.repository;

import com.example.web_ban_cong.model.Product;
import com.example.web_ban_cong.model.Tree;
import com.example.web_ban_cong.model.WishlistProduct;
import com.example.web_ban_cong.model.WishlistTree;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistTreeRepository extends JpaRepository<WishlistTree, Long> {

    public List<WishlistTree> findByUserId(Long id);

    @Transactional
    public void deleteByTree (Tree tree);
}
