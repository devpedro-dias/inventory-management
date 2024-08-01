package dev.pedrodias.inventory_management.controller;

import dev.pedrodias.inventory_management.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/totalInventory")
    public int getTotalInventory() {
        return inventoryService.calculateTotalInventory();
    }

    @GetMapping("/valueTotalInventory")
    public double getValueTotalInventory() {
        return inventoryService.calculateValueTotalInventory();
    }
}
