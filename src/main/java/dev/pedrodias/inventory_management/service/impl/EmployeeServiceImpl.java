package dev.pedrodias.inventory_management.service.impl;

import dev.pedrodias.inventory_management.controller.exception.ResourceNotFoundException;
import dev.pedrodias.inventory_management.model.Employee;
import dev.pedrodias.inventory_management.repository.EmployeeRepository;
import dev.pedrodias.inventory_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
    }

    @Transactional
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));

        existingEmployee.setName(employee.getName());
        existingEmployee.setCpf(employee.getCpf());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhone(employee.getPhone());
        existingEmployee.setZipCode(employee.getZipCode());
        existingEmployee.setPassword(employee.getPassword());


        return employeeRepository.save(existingEmployee);
    }

    @Transactional
    public boolean deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
        employeeRepository.delete(employee);
        return false;
    }

    @Transactional
    public Employee findById(Long customerId) {
        Optional<Employee> optionalCustomer = employeeRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new ResourceNotFoundException("Customer not found with id: " + customerId);
        }
    }
}
