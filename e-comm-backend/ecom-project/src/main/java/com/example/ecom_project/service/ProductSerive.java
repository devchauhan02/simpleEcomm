package com.example.ecom_project.service;

import com.example.ecom_project.model.ProductModel;
import com.example.ecom_project.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSerive {
    @Autowired
    private ProductRepo repo;
    public List<ProductModel> getAllProducts(){
        return repo.findAll();
    }

    public ProductModel getProductByID(int id){
        return repo.findById(id).orElse(null);
    }
}
