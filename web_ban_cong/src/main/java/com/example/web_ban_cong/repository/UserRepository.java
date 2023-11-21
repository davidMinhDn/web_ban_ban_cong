package com.example.web_ban_cong.repository;

import com.example.web_ban_cong.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository< User, Long> {
    public User findByMail(String mail);
}
