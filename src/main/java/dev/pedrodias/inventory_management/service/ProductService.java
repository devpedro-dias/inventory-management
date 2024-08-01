package dev.pedrodias.inventory_management.service;

import dev.pedrodias.inventory_management.dto.product.ProductDTO;
import dev.pedrodias.inventory_management.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProduct(Long id);

    Product createProduct(ProductDTO productDTO);

    Product updateProduct(Long id, ProductDTO productDTO);

    void deleteProduct(Long id);

    List<Product> findAllById(List<Long> productIds);
}

