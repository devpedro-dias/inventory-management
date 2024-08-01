package dev.pedrodias.inventory_management.model;

import dev.pedrodias.inventory_management.dto.movement.MovementDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movements")
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MovementType movementType;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Movement(MovementType movementType, Product product, int quantity, Date date) {
        this.movementType = movementType;
        this.product = product;
        this.quantity = quantity;
        this.date = date;
    }

    public MovementDTO toDTO() {
        return MovementDTO.fromMovement(this);
    }
}