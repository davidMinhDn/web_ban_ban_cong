package com.example.web_ban_cong.service;

import com.example.web_ban_cong.model.Manager;
import com.example.web_ban_cong.model.User;
import com.example.web_ban_cong.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagerService {
    @Autowired
    private ManagerRepository managerRepository;
    public Manager loginByMailAndPass(String mail, String pass) {
        Optional<Manager> managerOptional = Optional.ofNullable(managerRepository.findByMailAndPassword(mail, pass));
        return managerOptional.orElse(null);
    }
}
