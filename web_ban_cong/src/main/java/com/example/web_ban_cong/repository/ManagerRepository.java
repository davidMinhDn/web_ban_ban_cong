package com.example.web_ban_cong.repository;

import com.example.web_ban_cong.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository  extends JpaRepository<Manager, Long> {
    public Manager findByMailAndPassword(String mail , String password);
}
