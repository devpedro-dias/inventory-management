package dev.pedrodias.inventory_management.controller;

import dev.pedrodias.inventory_management.model.Employee;
import dev.pedrodias.inventory_management.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/employees"})
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    public EmployeeController() {
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = this.employeeService.getAllEmployees();
        return new ResponseEntity(employees, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        Employee employee = this.employeeService.getEmployeeById(id);
        return employee != null ? new ResponseEntity(employee, HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = this.employeeService.createEmployee(employee);
        return new ResponseEntity(createdEmployee, HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        Employee updatedEmployee = this.employeeService.updateEmployee(id, employee);
        return updatedEmployee != null ? new ResponseEntity(updatedEmployee, HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
        this.employeeService.deleteEmployee(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
