package dev.pedrodias.inventory_management.dto.inventory;

import dev.pedrodias.inventory_management.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {

    private Long id;

    private Product product;

    private int quantity;

}