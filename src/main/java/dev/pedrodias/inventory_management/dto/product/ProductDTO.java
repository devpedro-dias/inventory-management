package dev.pedrodias.inventory_management.dto.product;

import dev.pedrodias.inventory_management.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    private String name;

    private double price;

    private int quantity;

    private Long categoryId;


    public ProductDTO(String name, double price, int quantity, Long id) {
    }

    public static ProductDTO fromProduct(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory() != null ? product.getCategory().getId() : null
        );
    }
}
