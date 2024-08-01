package dev.pedrodias.inventory_management.controller;

import dev.pedrodias.inventory_management.dto.order.OrderDTO;
import dev.pedrodias.inventory_management.dto.order.OrderDTOConverter;
import dev.pedrodias.inventory_management.model.Order;
import dev.pedrodias.inventory_management.service.OrderService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@NoArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDTOConverter orderDTOConverter;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<Order> orders = this.orderService.getAllOrders();
        List<OrderDTO> orderDTOs = orders.stream()
                .map(orderDTOConverter::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        Order order = this.orderService.getOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(orderDTOConverter.toDTO(order));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = orderDTOConverter.toEntity(orderDTO);
        Order createdOrder = this.orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDTOConverter.toDTO(createdOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        Order order = orderDTOConverter.toEntity(orderDTO);
        Order updatedOrder = this.orderService.updateOrder(id, order);
        if (updatedOrder != null) {
            return ResponseEntity.ok(orderDTOConverter.toDTO(updatedOrder));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOrder(@PathVariable Long id) {
        boolean deleted = this.orderService.deleteOrder(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}