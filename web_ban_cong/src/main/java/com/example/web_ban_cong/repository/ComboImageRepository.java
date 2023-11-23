package com.example.web_ban_cong.repository;

import com.example.web_ban_cong.model.ComboImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComboImageRepository extends JpaRepository<ComboImage, Long> {
    List<ComboImage> findByComboId(Long combo_id);
}
