package com.example.web_ban_cong.service;

import com.example.web_ban_cong.model.Category;
import com.example.web_ban_cong.model.CategoryTree;

import java.util.List;

public interface ICategoryTreeService {
    public List<CategoryTree> getAllCategory ();
    public CategoryTree getCategoryById(Long id);
    public CategoryTree addCategory(CategoryTree categoryTree);


}
