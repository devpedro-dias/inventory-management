package dev.pedrodias.inventory_management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.pedrodias.inventory_management.dto.product.ProductDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    private int quantity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(String name, double price, int quantity, Category category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public static ProductDTO toDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory() != null ? product.getCategory().getId() : null
        );
    }

    public static Product fromDTO(ProductDTO dto, Category category) {
        return new Product(
                dto.getName(),
                dto.getPrice(),
                dto.getQuantity(),
                category
        );
    }
}