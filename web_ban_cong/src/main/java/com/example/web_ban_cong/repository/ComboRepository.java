package com.example.web_ban_cong.repository;

import com.example.web_ban_cong.model.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboRepository extends JpaRepository<Combo, Long>
{
}
