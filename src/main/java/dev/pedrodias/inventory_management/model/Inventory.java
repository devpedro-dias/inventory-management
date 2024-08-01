package dev.pedrodias.inventory_management.model;

import dev.pedrodias.inventory_management.dto.inventory.InventoryDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    public InventoryDTO toDTO() {
        return new InventoryDTO(this.id, this.product , this.quantity);
    }

    public static Inventory fromDTO(InventoryDTO dto) {
        return new Inventory(dto.getId(),dto.getProduct(), dto.getQuantity());
    }
}
