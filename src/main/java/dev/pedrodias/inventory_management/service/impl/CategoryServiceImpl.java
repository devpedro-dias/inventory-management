package dev.pedrodias.inventory_management.service.impl;

import dev.pedrodias.inventory_management.controller.exception.ResourceNotFoundException;
import dev.pedrodias.inventory_management.model.Category;
import dev.pedrodias.inventory_management.model.Product;
import dev.pedrodias.inventory_management.repository.CategoryRepository;
import dev.pedrodias.inventory_management.repository.ProductRepository;
import dev.pedrodias.inventory_management.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
        return productRepository.findByCategory(category);
    }

    @Transactional
    public Category updateCategory(Long id, Category updatedCategory) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found with ID: " + id);
        }

        updatedCategory.setId(id); // Ensure the ID is preserved
        return categoryRepository.save(updatedCategory);
    }

    @Transactional
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found with ID: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
