package com.example.ecom_project.controller;

import com.example.ecom_project.model.ProductModel;
import com.example.ecom_project.service.ProductSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductSerive service;

    @GetMapping("/")
    public String greet(){
        return "Greetings ..";
    }

    @GetMapping("/allProducts")
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts() , HttpStatus.OK);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductModel> getProduct(@PathVariable int id){
        ProductModel prod = service.getProductByID(id);
        return prod == null ? new ResponseEntity<>(service.getProductByID(id), HttpStatus.NOT_FOUND) : new ResponseEntity<>(service.getProductByID(id), HttpStatus.OK) ;
    }
}
