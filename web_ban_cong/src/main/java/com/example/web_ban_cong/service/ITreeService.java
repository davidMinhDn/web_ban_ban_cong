package com.example.web_ban_cong.service;

import com.example.web_ban_cong.model.Tree;

import java.util.List;

public interface ITreeService {
    public Tree getTreeById (Long id);
    public List<Tree> getAllTree();
    public void addTree(Tree tree);
    public List<Tree> getTreeByCategory(Long categoryId);
}
