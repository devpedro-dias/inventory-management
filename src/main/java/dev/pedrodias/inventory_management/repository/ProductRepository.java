package dev.pedrodias.inventory_management.repository;

import dev.pedrodias.inventory_management.model.Category;
import dev.pedrodias.inventory_management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(Category category);
}