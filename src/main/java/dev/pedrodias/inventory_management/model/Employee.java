package dev.pedrodias.inventory_management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "employees")
public class Employee extends User {
    @Column(unique = true)
    private String cpf;

    public Employee(Long id, String name, String email, String password, String phone, String address, String zipCode, String cpf) {
        super(id, name, email, password, phone, address, zipCode);
        this.cpf = cpf;
    }
}