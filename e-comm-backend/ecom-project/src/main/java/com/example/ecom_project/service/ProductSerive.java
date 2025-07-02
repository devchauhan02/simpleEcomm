package com.example.ecom_project.service;

import com.example.ecom_project.model.ProductModel;
import com.example.ecom_project.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public ProductModel addProduct(ProductModel prod, MultipartFile imageFile) throws IOException {
        prod.setImageName(imageFile.getOriginalFilename()); // ✅ fix here
        prod.setImageType(imageFile.getContentType());
        prod.setImageData(imageFile.getBytes());
        return repo.save(prod);
    }

}
