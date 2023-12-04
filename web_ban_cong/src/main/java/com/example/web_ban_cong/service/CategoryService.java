package com.example.web_ban_cong.service;

import com.example.web_ban_cong.model.Category;
import com.example.web_ban_cong.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryId(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
}
