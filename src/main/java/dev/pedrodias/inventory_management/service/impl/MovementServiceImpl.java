package dev.pedrodias.inventory_management.service.impl;

import dev.pedrodias.inventory_management.controller.exception.InsufficientStockException;
import dev.pedrodias.inventory_management.controller.exception.ResourceNotFoundException;
import dev.pedrodias.inventory_management.dto.movement.MovementDTO;
import dev.pedrodias.inventory_management.model.Movement;
import dev.pedrodias.inventory_management.model.MovementType;
import dev.pedrodias.inventory_management.model.Product;
import dev.pedrodias.inventory_management.repository.MovementRepository;
import dev.pedrodias.inventory_management.repository.ProductRepository;
import dev.pedrodias.inventory_management.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MovementServiceImpl implements MovementService {

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public Movement registerEntry(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        Movement movement = new Movement(MovementType.IN, product, quantity, new Date());
        return movementRepository.save(movement);
    }

    @Transactional
    @Override
    public Movement registerExit(Long productId, int quantity) {
        if (!validateExit(productId, quantity)) {
            throw new InsufficientStockException("Insufficient stock for product ID: " + productId);
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Movement movement = new Movement(MovementType.OUT, product, quantity, new Date());
        return movementRepository.save(movement);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Movement> getAllMovements() {
        return movementRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Movement getMovementById(Long id) {
        return movementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movement not found with ID: " + id));
    }

    @Transactional
    @Override
    public Movement updateMovement(Long id, MovementDTO movementDTO) {
        Movement existingMovement = movementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movement not found with ID: " + id));

        Product product = productRepository.findById(movementDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        existingMovement.setMovementType(MovementType.valueOf(movementDTO.getMovementType()));
        existingMovement.setProduct(product);
        existingMovement.setQuantity(movementDTO.getQuantity());
        existingMovement.setDate(movementDTO.getDate());

        return movementRepository.save(existingMovement);
    }

    @Transactional
    @Override
    public Movement createMovement(MovementDTO movementDTO) {
        Product product = productRepository.findById(movementDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Movement movement = new Movement(
                MovementType.valueOf(movementDTO.getMovementType()),
                product,
                movementDTO.getQuantity(),
                movementDTO.getDate()
        );

        return movementRepository.save(movement);
    }

    @Transactional
    @Override
    public void deleteMovement(Long id) {
        Movement movement = movementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movement not found with ID: " + id));
        movementRepository.delete(movement);
    }

    @Transactional(readOnly = true)
    public boolean validateExit(Long productId, int quantity) {
        int currentStock = calculateCurrentStock(productId);
        return currentStock >= quantity;
    }

    @Transactional(readOnly = true)
    public int calculateCurrentStock(Long productId) {
        List<Movement> movements = movementRepository.findProductById(productId);
        return movements.stream()
                .mapToInt(movement -> movement.getMovementType() == MovementType.IN ? movement.getQuantity() : -movement.getQuantity())
                .sum();
    }
}