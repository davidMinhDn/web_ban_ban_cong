package com.example.web_ban_cong.service;

import com.example.web_ban_cong.model.Combo;
import com.example.web_ban_cong.repository.ComboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComboService  implements IComboService{
    @Autowired
    private ComboRepository comboRepository;

    @Override
    public Combo getComboById(Long id) {
        Optional<Combo> comboOptional = comboRepository.findById(id);
        return comboOptional.orElse(null);
    }

    @Override
    public List<Combo> getAllCombo() {
        return comboRepository.findAll();
    }

}
