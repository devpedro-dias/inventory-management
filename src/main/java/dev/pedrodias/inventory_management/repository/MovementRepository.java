package dev.pedrodias.inventory_management.repository;

import dev.pedrodias.inventory_management.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    List<Movement> findProductById(Long productId);
}