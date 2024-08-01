package dev.pedrodias.inventory_management.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.pedrodias.inventory_management.dto.category.CategoryDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public CategoryDTO toDTO() {
        return new CategoryDTO(this.id, this.name);
    }

    public static Category fromDTO(CategoryDTO dto) {
        return new Category(dto.getId(), dto.getName(), null);
    }
}