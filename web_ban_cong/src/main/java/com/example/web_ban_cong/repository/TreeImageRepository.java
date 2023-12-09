package com.example.web_ban_cong.repository;

import com.example.web_ban_cong.model.ProductImage;
import com.example.web_ban_cong.model.Tree;
import com.example.web_ban_cong.model.TreeImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreeImageRepository extends JpaRepository<TreeImage, Long> {
    List<TreeImage> findByTreeId(Long id);
}
