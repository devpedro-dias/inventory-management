package dev.pedrodias.inventory_management.service;

import dev.pedrodias.inventory_management.model.Category;
import dev.pedrodias.inventory_management.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProduct(Long id);

    Product createProduct(Product product);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    List<Product> getProductsByCategory(Category category);
}
