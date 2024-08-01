package dev.pedrodias.inventory_management.controller;

import dev.pedrodias.inventory_management.controller.exception.InsufficientStockException;
import dev.pedrodias.inventory_management.controller.exception.ResourceNotFoundException;
import dev.pedrodias.inventory_management.dto.movement.MovementDTO;
import dev.pedrodias.inventory_management.model.Movement;
import dev.pedrodias.inventory_management.model.Product;
import dev.pedrodias.inventory_management.service.MovementService;
import dev.pedrodias.inventory_management.service.ProductService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@NoArgsConstructor
@RequestMapping("/api/movements")
public class MovementController {
    @Autowired
    private MovementService movementService;

    @Autowired
    private ProductService productService;

    @PostMapping("/entry/{productId}")
    public ResponseEntity<MovementDTO> registerEntry(@PathVariable("productId") Long productId, @RequestParam("quantity") int quantity) {
        try {
            Movement movement = this.movementService.registerEntry(productId, quantity);
            return new ResponseEntity<>(MovementDTO.fromMovement(movement), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/exit/{productId}")
    public ResponseEntity<MovementDTO> registerExit(@PathVariable("productId") Long productId, @RequestParam("quantity") int quantity) {
        try {
            Movement movement = this.movementService.registerExit(productId, quantity);
            return new ResponseEntity<>(MovementDTO.fromMovement(movement), HttpStatus.CREATED);
        } catch (InsufficientStockException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<MovementDTO>> getAllMovements() {
        List<Movement> movements = this.movementService.getAllMovements();
        List<MovementDTO> movementDTOs = movements.stream()
                .map(MovementDTO::fromMovement)
                .collect(Collectors.toList());
        return new ResponseEntity<>(movementDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovementDTO> getMovementById(@PathVariable("id") Long id) {
        try {
            Movement movement = this.movementService.getMovementById(id);
            return new ResponseEntity<>(MovementDTO.fromMovement(movement), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovementDTO> updateMovement(@PathVariable("id") Long id, @RequestBody MovementDTO movementDTO) {
        try {
            Product product = productService.getProduct(movementDTO.getProductId());
            Movement updatedMovement = this.movementService.updateMovement(id, movementDTO);
            return new ResponseEntity<>(MovementDTO.fromMovement(updatedMovement), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<MovementDTO> createMovement(@RequestBody MovementDTO movementDTO) {
        try {
            Product product = productService.getProduct(movementDTO.getProductId());
            Movement createdMovement = this.movementService.createMovement(movementDTO);
            return new ResponseEntity<>(MovementDTO.fromMovement(createdMovement), HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovement(@PathVariable("id") Long id) {
        try {
            this.movementService.deleteMovement(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
