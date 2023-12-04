package com.example.web_ban_cong.repository;

import com.example.web_ban_cong.model.Category;
import com.example.web_ban_cong.model.ComboImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
