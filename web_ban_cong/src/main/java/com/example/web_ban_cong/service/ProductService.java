package com.example.web_ban_cong.service;

import com.example.web_ban_cong.model.Product;
import com.example.web_ban_cong.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService implements IProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product getProductByID(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        return null;
    }
}
