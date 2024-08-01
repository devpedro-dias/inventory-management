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
@Table(name = "customers")
public class Customer extends User {

    @Column(unique = true)
    private String cnpj;

    public Customer(Long id, String name, String email, String password, String phone, String address, String zipCode, String cnpj) {
        super(id, name, email, password, phone, address, zipCode);
        this.cnpj = cnpj;
    }
}
