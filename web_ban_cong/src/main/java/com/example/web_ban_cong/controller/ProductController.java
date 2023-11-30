package com.example.web_ban_cong.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.web_ban_cong.model.Product;
import com.example.web_ban_cong.model.ProductImage;
import com.example.web_ban_cong.repository.ComboProductRepository;
import com.example.web_ban_cong.repository.ComboRepository;
import com.example.web_ban_cong.repository.ProductImageRepository;
import com.example.web_ban_cong.repository.ProductRepository;
import com.example.web_ban_cong.service.CloudinaryService;
import com.example.web_ban_cong.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private ComboProductRepository comboProductRepository;

    @GetMapping("/detail/{product_id}")
    public String detailProduct(@PathVariable(name = "product_id") Long id, Model model){
        Product product = productService.getProductByID(id);
        List<ProductImage> productImages = productImageRepository.findByProductId(id);
        model.addAttribute("product", product);
        model.addAttribute("productImages", productImages);
        return "front-end/product-bottom-thumbnail";
    }

    @GetMapping("/all")
    private ResponseEntity<?> getAllProduct(){
        List<Product> products = productService.getAllProduct();
        return ResponseEntity.ok(products);

    }
    @GetMapping("/add")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        return "back-end/add-new-product";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product
            , @RequestPart("file") MultipartFile file, @RequestParam("imageOther") MultipartFile[] images) throws IOException {
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

        // Lấy URL của file đã tải lên thành công
        String imageUrl = (String) uploadResult.get("secure_url");
        product.setImage(imageUrl);
        productRepository.save(product);
        for (MultipartFile image : images) {
            Map<?, ?> uploadResult1 = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());

            // Lấy URL của file đã tải lên thành công
            String imageUrlOther = (String) uploadResult.get("secure_url");
            ProductImage newProductImage;
            productImageRepository.save(ProductImage.builder().product(product).imageUrl(imageUrlOther).build());
        }

        return "back-end/add-new-product";
    }

    @GetMapping ("/update/{product_id}")
    public String updateProduct(@PathVariable("product_id") Long id, Model model){
        Product product = productService.getProductByID(id);
        model.addAttribute("product", product);
        return "back-end/update-product";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product
            , @RequestPart("file") MultipartFile file) throws IOException {
        System.out.println(product.getId());
        Product existProduct = productService.getProductByID(product.getId());
        existProduct.setName(product.getName());
        existProduct.setCategory(product.getCategory());
        existProduct.setColor(product.getColor());
        existProduct.setDescription(product.getDescription());
        existProduct.setQuantity(product.getQuantity());
        existProduct.setPrice(product.getPrice());
        existProduct.setTitle(product.getTitle());
        existProduct.setSize(product.getSize());
        existProduct.setManufacturing_date(product.getManufacturing_date());
        existProduct.setCach_bao_quan(product.getCach_bao_quan());
        existProduct.setShape(product.getShape());
        existProduct.setChat_lieu(product.getChat_lieu());
        existProduct.setDac_tinh(product.getDac_tinh());
        existProduct.setPriceOther(product.getPriceOther());
        existProduct.setRefund(product.isRefund());

        if(file.isEmpty()){
            productRepository.save(existProduct);
            return "redirect:/manager/all";
        }
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

        // Lấy URL của file đã tải lên thành công
        String imageUrl = (String) uploadResult.get("secure_url");
        existProduct.setImage(imageUrl);
        productRepository.save(existProduct);
        return "redirect:/manager/all";
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long id){

        productService.deleteProduct(id);
        return ResponseEntity.ok("Sản phẩm đã được xóa thành công");
    }

}
