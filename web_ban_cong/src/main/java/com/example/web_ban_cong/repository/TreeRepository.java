package com.example.web_ban_cong.repository;

import com.example.web_ban_cong.model.Product;
import com.example.web_ban_cong.model.Tree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreeRepository extends JpaRepository<Tree, Long> {
    List<Tree> findByCombos_Id(Long comboId);
}
