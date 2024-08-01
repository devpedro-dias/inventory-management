package dev.pedrodias.inventory_management.dto.order;

import dev.pedrodias.inventory_management.model.*;
import dev.pedrodias.inventory_management.service.CustomerService;
import dev.pedrodias.inventory_management.service.EmployeeService;
import dev.pedrodias.inventory_management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDTOConverter {

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    public OrderDTO toDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getOrderStatus().name(),
                order.getDateTime(),
                order.getProducts().stream().map(Product::getId).collect(Collectors.toList()),
                order.getCustomer() != null ? order.getCustomer().getId() : null,
                order.getEmployee() != null ? order.getEmployee().getId() : null
        );
    }

    public Order toEntity(OrderDTO orderDTO) {
        List<Product> products = productService.findAllById(orderDTO.getProductIds());
        Customer customer = orderDTO.getCustomerId() != null ? customerService.findById(orderDTO.getCustomerId()) : null;
        Employee employee = orderDTO.getEmployeeId() != null ? employeeService.findById(orderDTO.getEmployeeId()) : null;

        return new Order(
                orderDTO.getId(),
                OrderStatus.valueOf(orderDTO.getOrderStatus()),
                orderDTO.getDateTime(),
                products,
                customer,
                employee
        );
    }
}

