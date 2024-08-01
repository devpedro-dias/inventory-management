package dev.pedrodias.inventory_management.dto.customer;

import dev.pedrodias.inventory_management.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerDTO extends UserDTO {
    
    private String cnpj;

    public CustomerDTO(Long id, String name, String email, String phone, String address, String zipCode, String cnpj) {
    }
}
