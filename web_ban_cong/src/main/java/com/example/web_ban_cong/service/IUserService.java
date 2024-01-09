package com.example.web_ban_cong.service;

import com.example.web_ban_cong.model.User;

public interface IUserService {
    public User addUser(User user);
    public User loginByMailAndPass(String user, String pass);
}
