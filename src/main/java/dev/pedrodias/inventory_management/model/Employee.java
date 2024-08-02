package dev.pedrodias.inventory_management.model;

import dev.pedrodias.inventory_management.dto.employee.EmployeeDTO;
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
@Table(name = "employees")
public class Employee extends User {

    @Column(unique = true)
    private String cpf;

    private String password;

    public EmployeeDTO toDTO() {
        return new EmployeeDTO(
                this.getId(),
                this.getName(),
                this.getEmail(),
                this.getPhone(),
                this.getAddress(),
                this.getZipCode(),
                this.cpf,
                this.password
        );
    }

    public static Employee fromDTO(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setAddress(dto.getAddress());
        employee.setZipCode(dto.getZipCode());
        employee.setCpf(dto.getCpf());
        employee.setPassword(dto.getPassword());
        return employee;
    }
}