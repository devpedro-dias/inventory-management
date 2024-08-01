package dev.pedrodias.inventory_management.service;

import dev.pedrodias.inventory_management.model.Category;
import dev.pedrodias.inventory_management.model.Product;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);

    List<Category> getAllCategories();

    List<Product> getProductsByCategory(Long categoryId);

    Category getCategoryById(Long categoryId);

    Category updateCategory(Long id, Category category);

    void deleteCategory(Long id);
}
