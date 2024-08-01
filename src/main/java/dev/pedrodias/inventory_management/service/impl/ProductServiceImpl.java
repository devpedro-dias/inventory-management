package dev.pedrodias.inventory_management.service.impl;

import dev.pedrodias.inventory_management.controller.exception.ResourceNotFoundException;
import dev.pedrodias.inventory_management.dto.product.ProductDTO;
import dev.pedrodias.inventory_management.model.Category;
import dev.pedrodias.inventory_management.model.Product;
import dev.pedrodias.inventory_management.repository.CategoryRepository;
import dev.pedrodias.inventory_management.repository.ProductRepository;
import dev.pedrodias.inventory_management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Product createProduct(ProductDTO productDTO) {
        Category category = null;
        if (productDTO.getCategoryId() != null) {
            category = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + productDTO.getCategoryId()));
        }

        Product product = new Product(
                productDTO.getName(),
                productDTO.getPrice(),
                productDTO.getQuantity(),
                category
        );
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
    }

    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }


    @Transactional
    public List<Product> findAllById(List<Long> productIds) {
        return productRepository.findAllById(productIds);
    }

    @Transactional
    public Product updateProduct(Long id, ProductDTO updateProductDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        existingProduct.setName(updateProductDTO.getName());
        existingProduct.setPrice(updateProductDTO.getPrice());
        existingProduct.setQuantity(updateProductDTO.getQuantity());

        if (updateProductDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(updateProductDTO.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + updateProductDTO.getCategoryId()));
            existingProduct.setCategory(category);
        }

        return productRepository.save(existingProduct);
    }

    @Transactional
    public int calculateTotalInventory() {
        return productRepository.findAll().stream()
                .mapToInt(Product::getQuantity)
                .sum();
    }

    @Transactional
    public double calculateValueTotalInventory() {
        return productRepository.findAll().stream()
                .mapToDouble(product -> product.getQuantity() * product.getPrice())
                .sum();
    }
}
