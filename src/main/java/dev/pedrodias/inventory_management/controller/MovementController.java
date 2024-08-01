package dev.pedrodias.inventory_management.controller;

import dev.pedrodias.inventory_management.controller.exception.InsufficientStockException;
import dev.pedrodias.inventory_management.controller.exception.ResourceNotFoundException;
import dev.pedrodias.inventory_management.model.Movement;
import dev.pedrodias.inventory_management.service.MovementService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/movements"})
public class MovementController {
    @Autowired
    private MovementService movementService;

    public MovementController() {
    }

    @PostMapping({"/entry/{productId}"})
    public ResponseEntity<Movement> registerEntry(@PathVariable("productId") Long productId, @RequestParam("quantity") int quantity) {
        try {
            Movement movement = this.movementService.registerEntry(productId, quantity);
            return new ResponseEntity(movement, HttpStatus.CREATED);
        } catch (IllegalArgumentException var4) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping({"/exit/{productId}"})
    public ResponseEntity<Movement> registerExit(@PathVariable("productId") Long productId, @RequestParam("quantity") int quantity) {
        try {
            Movement movement = this.movementService.registerExit(productId, quantity);
            return new ResponseEntity(movement, HttpStatus.CREATED);
        } catch (InsufficientStockException var4) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException var5) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Movement>> getAllMovements() {
        List<Movement> movements = this.movementService.getAllMovements();
        return new ResponseEntity(movements, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Movement> getMovementById(@PathVariable("id") Long id) {
        try {
            Movement movement = this.movementService.getMovementById(id);
            return new ResponseEntity(movement, HttpStatus.OK);
        } catch (ResourceNotFoundException var3) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Movement> updateMovement(@PathVariable("id") Long id, @RequestBody Movement movement) {
        try {
            Movement updatedMovement = this.movementService.updateMovement(id, movement);
            return new ResponseEntity(updatedMovement, HttpStatus.OK);
        } catch (ResourceNotFoundException var4) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Movement> createMovement(@RequestBody Movement movement) {
        Movement createdMovement = this.movementService.createMovement(movement);
        return new ResponseEntity(createdMovement, HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteMovement(@PathVariable("id") Long id) {
        try {
            this.movementService.deleteMovement(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException var3) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}