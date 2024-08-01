package dev.pedrodias.inventory_management.dto.movement;

import dev.pedrodias.inventory_management.model.Movement;
import dev.pedrodias.inventory_management.model.MovementType;
import dev.pedrodias.inventory_management.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovementDTO {
    private Long id;
    private String movementType;
    private Long productId;
    private int quantity;
    private Date date;

    public static MovementDTO fromMovement(Movement movement) {
        return new MovementDTO(
                movement.getId(),
                movement.getMovementType().name(),
                movement.getProduct() != null ? movement.getProduct().getId() : null,
                movement.getQuantity(),
                movement.getDate()
        );
    }

    public Movement toMovement(Product product) {
        return new Movement(
                MovementType.valueOf(this.movementType),
                product,
                this.quantity,
                this.date
        );
    }
}
