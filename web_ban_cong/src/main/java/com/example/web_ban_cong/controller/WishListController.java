package com.example.web_ban_cong.controller;

import com.example.web_ban_cong.model.*;
import com.example.web_ban_cong.repository.ProductRepository;
import com.example.web_ban_cong.repository.TreeRepository;
import com.example.web_ban_cong.repository.WishListProductRepository;
import com.example.web_ban_cong.repository.WishlistTreeRepository;
import com.example.web_ban_cong.service.ProductService;
import com.example.web_ban_cong.service.TreeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/wishlist")
public class WishListController {
    @Autowired
    private WishListProductRepository wishListProductRepository;
    @Autowired
    private WishlistTreeRepository wishlistTreeRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private TreeService treeService;
    @PostMapping("/product/add/{productId}")
    public ResponseEntity<?> addWishlistPro(@PathVariable(name = "productId") Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            ResponseEntity.ok("Bạn chưa đăng nhập !!");
        }else {
            List<WishlistProduct> wishlistProducts = wishListProductRepository.findByUserId(user.getId());
            Product product = productService.getProductByID(id);
            boolean checkProExist = true;
            for (WishlistProduct wishlistProduct:
                 wishlistProducts) {
                if (Objects.equals(wishlistProduct.getProduct().getId(), id)) {
                    checkProExist = false;
                    break;
                }
            }
            if(checkProExist){
                wishListProductRepository.save(WishlistProduct.builder().product(product).user(user).build());
                ResponseEntity.ok("Thêm vào Wishlist thành công !");
            }else {
                wishListProductRepository.deleteByProduct(product);
                ResponseEntity.ok("Xóa vào Wishlist thành công !");
            }
        }
        return null;
    }

    @PostMapping("/tree/add/{treeId}")
    public ResponseEntity<?> addWishlistTree(@PathVariable(name = "treeId") Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            ResponseEntity.ok("Bạn chưa đăng nhập !!");
        }else {
            List<WishlistTree> wishlistTrees = wishlistTreeRepository.findByUserId(user.getId());
            Tree tree = treeService.getTreeById(id);
            boolean checkTreeExist = true;
            for (WishlistTree wishlistTree:
                    wishlistTrees) {
                if (Objects.equals(wishlistTree.getTree().getId(), id)) {
                    checkTreeExist = false;
                    break;
                }
            }
            if(checkTreeExist){
                wishlistTreeRepository.save(WishlistTree.builder().tree(tree).user(user).build());
                ResponseEntity.ok("Thêm vào Wishlist thành công !");
            }else {
                wishlistTreeRepository.deleteByTree(tree);
                ResponseEntity.ok("Xóa vào Wishlist thành công !");
            }
        }
        return null;
    }

}
