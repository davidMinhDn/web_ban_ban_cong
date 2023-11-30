package com.example.web_ban_cong.service;

import com.example.web_ban_cong.model.Tree;

import java.util.List;
import java.util.Optional;

public interface ITreeService {
    public Tree getTreeById (Long id);
    public List<Tree> getAllTree();
    public Tree addTree(Tree tree);
}
