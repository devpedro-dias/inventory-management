package dev.pedrodias.inventory_management.dto.order;

import dev.pedrodias.inventory_management.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;

    private String orderStatus;

    private LocalDateTime dateTime;

    private List<Long> productIds;

    private Long customerId;

    private Long employeeId;

    public static OrderDTO fromOrder(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getOrderStatus() != null ? order.getOrderStatus().name() : null,
                order.getDateTime(),
                order.getProducts() != null ? order.getProducts().stream()
                        .map(Product::getId)
                        .collect(Collectors.toList()) : Collections.emptyList(),
                order.getCustomer() != null ? order.getCustomer().getId() : null,
                order.getEmployee() != null ? order.getEmployee().getId() : null
        );
    }

    public Order toOrder(Customer customer, Employee employee, List<Product> products) {
        return new Order(
                this.id,
                OrderStatus.valueOf(this.orderStatus),
                this.dateTime,
                products,
                customer,
                employee
        );
    }
}
