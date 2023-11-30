package com.example.web_ban_cong.service;

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
    public Tree addTree(Tree tree) {
        return treeRepository.save(tree);
    }


}
