package com.example.web_ban_cong.repository;

import com.example.web_ban_cong.model.CategoryTree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryTreeRepository extends JpaRepository<CategoryTree, Long> {
}
