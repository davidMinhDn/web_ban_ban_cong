package com.example.web_ban_cong.service;

import com.example.web_ban_cong.model.Category;
import com.example.web_ban_cong.model.Product;
import com.example.web_ban_cong.repository.ComboProductRepository;
import com.example.web_ban_cong.repository.ProductImageRepository;
import com.example.web_ban_cong.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private ComboProductRepository comboProductRepository;
    @Autowired
    private CategoryService categoryService;
    @Override
    public Product getProductByID(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElse(null);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductByCategory(Long id) {
        return productRepository.findByCategory_id(id);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productImageRepository.deleteByProductId(id);
        comboProductRepository.deleteByProductId(id);
        productRepository.deleteById(id);
    }
}
