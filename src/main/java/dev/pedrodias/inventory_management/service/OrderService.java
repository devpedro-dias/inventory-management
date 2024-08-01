package dev.pedrodias.inventory_management.service;

import dev.pedrodias.inventory_management.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();

    Order getOrderById(Long id);

    Order createOrder(Order order);

    Order updateOrder(Long id, Order order);

    boolean deleteOrder(Long id);
}