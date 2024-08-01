package dev.pedrodias.inventory_management.service.impl;

import dev.pedrodias.inventory_management.model.Product;
import dev.pedrodias.inventory_management.repository.ProductRepository;
import dev.pedrodias.inventory_management.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true) // Leitura apenas
    public int calculateTotalInventory() {
        List<Product> products = productRepository.findAll();
        return products.stream().mapToInt(Product::getQuantity).sum();
    }

    @Transactional(readOnly = true) // Leitura apenas
    public double calculateValueTotalInventory() {
        List<Product> products = productRepository.findAll();
        return products.stream().mapToDouble(product -> product.getQuantity() * product.getPrice()).sum();
    }
}
