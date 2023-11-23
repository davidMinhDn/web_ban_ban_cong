package com.example.web_ban_cong.service;

import com.example.web_ban_cong.model.Product;

import java.util.List;

public interface IProductService {
    public Product getProductByID(Long id);
    public List<Product> getAllProduct();
}
