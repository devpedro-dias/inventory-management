package dev.pedrodias.inventory_management.service;

import dev.pedrodias.inventory_management.dto.movement.MovementDTO;
import dev.pedrodias.inventory_management.model.Movement;

import java.util.List;

public interface MovementService {

    Movement registerEntry(Long productId, int quantity);

    Movement registerExit(Long productId, int quantity);

    List<Movement> getAllMovements();

    Movement getMovementById(Long id);

    Movement updateMovement(Long id, MovementDTO movementDTO);

    Movement createMovement(MovementDTO movementDTO);

    void deleteMovement(Long id);
}