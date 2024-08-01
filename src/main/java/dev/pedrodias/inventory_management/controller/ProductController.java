package dev.pedrodias.inventory_management.controller;

import dev.pedrodias.inventory_management.model.Category;
import dev.pedrodias.inventory_management.model.Product;
import dev.pedrodias.inventory_management.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/products"})
public class ProductController {
    @Autowired
    private ProductService productService;

    public ProductController() {
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = this.productService.getAllProducts();
        return new ResponseEntity(products, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product product = this.productService.getProduct(id);
        return product != null ? new ResponseEntity(product, HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = this.productService.createProduct(product);
        return new ResponseEntity(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        Product updatedProduct = this.productService.updateProduct(id, product);
        return updatedProduct != null ? new ResponseEntity(updatedProduct, HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        this.productService.deleteProduct(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping({"/category"})
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam Category category) {
        List<Product> products = this.productService.getProductsByCategory(category);
        return new ResponseEntity(products, HttpStatus.OK);
    }
}