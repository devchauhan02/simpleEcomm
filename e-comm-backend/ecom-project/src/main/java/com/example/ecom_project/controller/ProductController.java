package com.example.ecom_project.controller;

import com.example.ecom_project.model.ProductModel;
import com.example.ecom_project.service.ProductSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductSerive service;

    @RequestMapping("/")
    public String greet(){
        return "Greetings ..";
    }

    @RequestMapping("/allProducts")
    public List<ProductModel> getAllProducts(){
        return service.getAllProducts();
    }
}
