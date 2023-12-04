package com.example.web_ban_cong.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.web_ban_cong.model.*;
import com.example.web_ban_cong.repository.ComboImageRepository;
import com.example.web_ban_cong.repository.ComboProductRepository;
import com.example.web_ban_cong.repository.ProductRepository;
import com.example.web_ban_cong.repository.TreeRepository;
import com.example.web_ban_cong.service.ComboService;
import com.example.web_ban_cong.service.ProductService;
import com.example.web_ban_cong.service.TreeService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/combo")
public class ComboController {
    @Autowired
    private ComboService comboService;
    @Autowired
    private ComboImageRepository comboImageRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TreeRepository treeRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private TreeService treeService;
    @GetMapping("/detail/{comboId}")
    public String detailCombo(@PathVariable("comboId")Long id, Model model){
        List<Product> products = productRepository.findByCombos_Id(id);
        List<ComboImage> comboImages = comboImageRepository.findByComboId(id);
        Combo combo = comboService.getComboById(id);
        List<Tree> trees = treeRepository.findByCombos_Id(id);
        model.addAttribute("trees", trees);
        model.addAttribute("images", comboImages);
        model.addAttribute("products", products);
        model.addAttribute("combo", combo);
        return "front-end/product-slider";
    }

    @GetMapping("/manager/all")
    public String allCombo(Model model){
        return "back-end/combos";
    }

    @GetMapping("/add")
    public String createCombo(Model model){
        model.addAttribute("combo", new Combo());
        model.addAttribute("products", productRepository.findAll());
        return "back-end/add-new-combo";
    }

    @PostMapping("/add")
    public String createCombo( @ModelAttribute Combo combo, Model model
            , @RequestPart("file") MultipartFile file, @RequestParam("imageOther") MultipartFile[] images) throws IOException {
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

        // Lấy URL của file đã tải lên thành công
        String imageUrl = (String) uploadResult.get("secure_url");
        combo.setImage(imageUrl);
        comboService.addCombo(combo);
        for (MultipartFile image : images) {
            Map<?, ?> uploadResult1 = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());

            // Lấy URL của file đã tải lên thành công
            String imageUrlOther = (String) uploadResult1.get("secure_url");
            ProductImage newProductImage;
            comboImageRepository.save(ComboImage.builder().combo(combo).image(imageUrlOther).build());
        }
        model.addAttribute("combo", combo);
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("trees", treeRepository.findAll());
        return "back-end/add-continue-combo";
    }

    @PostMapping("addProduct/{comboId}")
    public ResponseEntity<String> addPorduct(@RequestBody Integer[] ids, @PathVariable(name = "comboId") Long comboId){
        Combo combo = comboService.getComboById(comboId);
        System.out.println("comboId :" + comboId);
        List<Product> products = new ArrayList<>();
        for (Integer i : ids){
            products.add(productService.getProductByID(Long.parseLong(i.toString())));
        }
        combo.setProducts(products);
        comboService.addCombo(combo);
        return ResponseEntity.ok("");
    }
    @PostMapping("addTree/{comboId}")
    public ResponseEntity<String> addTr(@RequestBody Integer[] ids, @PathVariable(name = "comboId") Long comboId){
        Combo combo = comboService.getComboById(comboId);
        List<Tree> trees = new ArrayList<>();
        for (Integer i : ids){
            trees.add(treeService.getTreeById(Long.parseLong(i.toString())));
        }
        combo.setFlowers_and_trees(trees);
        comboService.addCombo(combo);

        return ResponseEntity.ok("");
    }

    @GetMapping("/ajaxAddPro/{productId}")
    public ResponseEntity<String> addProduct(@PathVariable(name = "productId")Long id){
        Product product = productService.getProductByID(id);
        String text = "<tr name=\"" +
                product.getId() +
                "\">\n" +
                "        <td>\n" +
                "            <div class=\"table-image\">\n" +
                "                <img src=\"" +
                product.getImage() +
                "\" class=\"img-fluid\" alt=\"\">\n" +
                "            </div>\n" +
                "        </td>\n" +
                "        <td>" +
                product.getName() +
                "</td>\n" +
                "        <td>" +
                product.getColor() +
                "</td>\n" +
                "        <td>\n" +
                "            <ul>\n" +
                "                <li>\n" +
                "                    <a href=\"javascript:void(0)\" data-bs-toggle=\"modal\" onclick=\"deleteProduct(this)\">\n" +
                "                        <i class=\"ri-delete-bin-line\"></i>\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "            </ul>\n" +
                "        </td>\n" +
                "    </tr>";

        return ResponseEntity.ok(text);
    }
    @GetMapping("/ajaxAddTree/{treeId}")
    public ResponseEntity<String> addTree(@PathVariable(name = "treeId")Long id){
        Tree tree = treeService.getTreeById(id);
        String text = "<tr name=\"" +
                tree.getId() +
                "\">\n" +
                "        <td>\n" +
                "            <div class=\"table-image\">\n" +
                "                <img src=\"" +
                tree.getImage() +
                "\" class=\"img-fluid\" alt=\"\">\n" +
                "            </div>\n" +
                "        </td>\n" +
                "        <td>" +
                tree.getName() +
                "</td>\n" +
                "        <td>" +
                tree.getSize() +
                "</td>\n" +
                "        <td>\n" +
                "            <ul>\n" +
                "                <li>\n" +
                "                    <a href=\"javascript:void(0)\" data-bs-toggle=\"modal\" onclick=\"deleteProduct(this)\">\n" +
                "                        <i class=\"ri-delete-bin-line\"></i>\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "            </ul>\n" +
                "        </td>\n" +
                "    </tr>";

        return ResponseEntity.ok(text);
    }
}
