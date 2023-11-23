package com.example.web_ban_cong.service;

import com.example.web_ban_cong.model.Combo;

import java.util.List;

public interface IComboService {
     Combo getComboById (Long id);
     List<Combo> getAllCombo();
}
