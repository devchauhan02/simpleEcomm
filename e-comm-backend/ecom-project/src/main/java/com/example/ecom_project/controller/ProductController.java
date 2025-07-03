package com.example.ecom_project.controller;

import com.example.ecom_project.model.ProductModel;
import com.example.ecom_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String greet(){
        return "Greetings ..";
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts() , HttpStatus.OK);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductModel> getProduct(@PathVariable int id){
        ProductModel prod = service.getProductByID(id);
        return prod == null ? new ResponseEntity<>(service.getProductByID(id), HttpStatus.NOT_FOUND) : new ResponseEntity<>(service.getProductByID(id), HttpStatus.OK) ;
    }

    @PostMapping(value = "/product", consumes = "multipart/form-data")
    public ResponseEntity<?> addProduct(
            @RequestPart("product") ProductModel prod,
            @RequestPart("imageFile") MultipartFile imageFile) {
        try {
            ProductModel prod1 = service.addProduct(prod, imageFile);
            return new ResponseEntity<>(prod1, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("File upload error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{prodID}/image")
    public ResponseEntity<byte[]> getImageByProductID(@PathVariable int prodID){
        ProductModel prod = service.getProductByID(prodID);
        byte[] imageFile = prod.getImageData();

        return ResponseEntity.ok().contentType(MediaType.valueOf(prod.getImageType())).body(imageFile);
    }
    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id , @RequestPart("product") ProductModel prod,
                                                @RequestPart("imageFile") MultipartFile imageFile){
        try {
            ProductModel prod1 = service.updateProduct(id, prod, imageFile);
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("File upload error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
       ProductModel prod  = service.getProductByID(id);
       if(prod != null){
           service.deleteProduct(id);
           return new ResponseEntity<>("Deleted" , HttpStatus.OK);
       }
       else{
           return new ResponseEntity<>("Product Not Found" , HttpStatus.NOT_FOUND);
       }
    }
    @GetMapping("/products/search")
    public ResponseEntity<List<ProductModel>> searchProducts(@RequestParam String Keyword){
        System.out.println("searching with" + Keyword);
        List<ProductModel> prod = service.searchProducts(Keyword);
        return new ResponseEntity<>(prod , HttpStatus.OK);
    }
}


