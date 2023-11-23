package com.example.web_ban_cong.controller;

import com.example.web_ban_cong.model.Product;
import com.example.web_ban_cong.model.ProductImage;
import com.example.web_ban_cong.repository.ProductImageRepository;
import com.example.web_ban_cong.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductImageRepository productImageRepository;

    @GetMapping("/detail/{product_id}")
    public String detailProduct(@PathVariable(name = "product_id") Long id, Model model){
        Product product = productService.getProductByID(id);
        List<ProductImage> productImages = productImageRepository.findByProductId(id);
        model.addAttribute("product", product);
        model.addAttribute("productImages", productImages);
        return "front-end/product-bottom-thumbnail";
    }
}
