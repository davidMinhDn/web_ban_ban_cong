package com.example.web_ban_cong.service;

import com.example.web_ban_cong.model.CategoryTree;
import com.example.web_ban_cong.repository.CategoryTreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryTreeService implements  ICategoryTreeService{
    @Autowired
    private CategoryTreeRepository categoryTreeRepository;
    @Override
    public List<CategoryTree> getAllCategory() {
        return categoryTreeRepository.findAll();
    }

    @Override
    public CategoryTree getCategoryById(Long id) {
        Optional<CategoryTree> categoryTree = categoryTreeRepository.findById(id);
        return categoryTree.orElse(null);
    }

    @Override
    public CategoryTree addCategory(CategoryTree categoryTree) {
        return categoryTreeRepository.save(categoryTree);
    }
}
