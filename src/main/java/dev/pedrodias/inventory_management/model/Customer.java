package dev.pedrodias.inventory_management.model;

import dev.pedrodias.inventory_management.dto.customer.CustomerDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer extends User {

    @Column(unique = true)
    private String cnpj;

    public CustomerDTO toDTO() {
        return new CustomerDTO(
                this.getId(),
                this.getName(),
                this.getEmail(),
                this.getPhone(),
                this.getAddress(),
                this.getZipCode(),
                this.cnpj
        );
    }

    public static Customer fromDTO(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
        customer.setZipCode(dto.getZipCode());
        customer.setCnpj(dto.getCnpj());
        return customer;
    }
}
