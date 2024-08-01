package dev.pedrodias.inventory_management.repository;

import dev.pedrodias.inventory_management.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
