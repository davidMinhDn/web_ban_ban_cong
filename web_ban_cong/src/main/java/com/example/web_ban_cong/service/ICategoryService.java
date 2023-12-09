package com.example.web_ban_cong.service;

import com.example.web_ban_cong.model.Category;

import java.util.List;

public interface ICategoryService {
    public List<Category> getAllCategory();
    public Category getCategoryId(Long id);
    public void addCategory(Category category);

    public void deleteCategory(Long id);

}
