package com.example.web_ban_cong.service;

import com.example.web_ban_cong.model.User;
import com.example.web_ban_cong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public User loginByMailAndPass(String mail, String pass) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByMailAndPassword(mail, pass));
        return userOptional.orElse(null);
    }
}
