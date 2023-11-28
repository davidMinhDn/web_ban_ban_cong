package com.example.web_ban_cong.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.web_ban_cong.model.Product;
import com.example.web_ban_cong.model.ProductImage;
import com.example.web_ban_cong.repository.ProductImageRepository;
import com.example.web_ban_cong.repository.ProductRepository;
import com.example.web_ban_cong.service.CloudinaryService;
import com.example.web_ban_cong.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Cloudinary cloudinary;

    @GetMapping("/detail/{product_id}")
    public String detailProduct(@PathVariable(name = "product_id") Long id, Model model){
        Product product = productService.getProductByID(id);
        List<ProductImage> productImages = productImageRepository.findByProductId(id);
        model.addAttribute("product", product);
        model.addAttribute("productImages", productImages);
        return "front-end/product-bottom-thumbnail";
    }

    @GetMapping("/add")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        return "back-end/add-new-product";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product, @RequestPart("file") MultipartFile file) throws IOException {
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

        // Lấy URL của file đã tải lên thành công
        String imageUrl = (String) uploadResult.get("secure_url");
        product.setImage(imageUrl);
        productRepository.save(product);
        return "back-end/add-new-product";
    }

}
