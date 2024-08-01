package dev.pedrodias.inventory_management.controller;

import dev.pedrodias.inventory_management.model.Order;
import dev.pedrodias.inventory_management.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/orders"})
public class OrderController {
    @Autowired
    private OrderService orderService;

    public OrderController() {
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return this.orderService.getAllOrders();
    }

    @GetMapping({"/{id}"})
    public Order getOrderById(@PathVariable Long id) {
        return this.orderService.getOrderById(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return this.orderService.createOrder(order);
    }

    @PutMapping({"/{id}"})
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
        return this.orderService.updateOrder(id, order);
    }

    @DeleteMapping({"/{id}"})
    public void deleteOrder(@PathVariable Long id) {
        this.orderService.deleteOrder(id);
    }
}