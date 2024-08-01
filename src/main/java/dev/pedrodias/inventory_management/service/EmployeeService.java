package dev.pedrodias.inventory_management.service;

import dev.pedrodias.inventory_management.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee employee);

    boolean deleteEmployee(Long id);

    Employee findById(Long employeeId);
}
