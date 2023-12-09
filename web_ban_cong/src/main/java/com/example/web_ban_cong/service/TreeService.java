package com.example.web_ban_cong.service;

import com.example.web_ban_cong.model.CategoryTree;
import com.example.web_ban_cong.model.Tree;
import com.example.web_ban_cong.repository.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreeService implements ITreeService{
    @Autowired
    private TreeRepository treeRepository;
    @Autowired
    private CategoryTreeService categoryTreeService;
    @Override
    public Tree getTreeById(Long id) {
        Optional<Tree> treeOptional = treeRepository.findById(id);
        return treeOptional.orElse(null);
    }

    @Override
    public List<Tree> getAllTree() {
        return treeRepository.findAll();
    }

    @Override
    public void addTree(Tree tree) {
        treeRepository.save(tree);
    }

    @Override
    public List<Tree> getTreeByCategory(Long categoryId) {
        CategoryTree categoryTree = categoryTreeService.getCategoryById(categoryId);
        return treeRepository.findByCategoryTree(categoryTree);
    }


}
