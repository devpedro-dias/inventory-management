package dev.pedrodias.inventory_management.dto.employee;

import dev.pedrodias.inventory_management.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmployeeDTO extends UserDTO {

    private String cpf;

    private String password;

    public EmployeeDTO(Long id, String name, String email, String phone, String address, String zipCode, String cpf, String password) {
    }
}
