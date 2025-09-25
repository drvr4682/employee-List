package com.example.employee.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;

public class EmployeeService implements EmployeeRepository {

    private static HashMap<Integer, Employee> employeeList = new HashMap<>();
    int uniqueEmployeeId = 7;

    public EmployeeService() {
        employeeList.put(1, new Employee(1, "Liam Carter", "liam.carter@example.com", "Engineering"));
        employeeList.put(2, new Employee(2, "Olivia Walker", "olivia.walker@example.com", "Product Management"));
        employeeList.put(3, new Employee(3, "Noah Bennett", "noah.bennett@example.com", "Sales"));
        employeeList.put(4, new Employee(4, "Ava Mitchell", "ava.mitchell@example.com", "Human Resources"));
        employeeList.put(5, new Employee(5, "Ethan Foster", "ethan.foster@example.com", "Finance"));
        employeeList.put(6, new Employee(6, "Sophia Rivera", "sophia.rivera@example.com", "Operations"));
    }

    @Override
    public ArrayList<Employee> getEmployees() {
        return new ArrayList<>(employeeList.values());
    }

    @Override
    public Employee addEmployee(Employee employee) {
        employee.setEmployeeId(uniqueEmployeeId);
        employeeList.put(uniqueEmployeeId, employee);
        uniqueEmployeeId += 1;
        return employee;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        Employee employee = employeeList.get(employeeId);
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return employee;
    }

    @Override
    public Employee updateEmployee(int employeeId, Employee employee) {
        Employee existingEmployee = employeeList.get(employeeId);
        if (existingEmployee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (employee.getEmployeeName() != null) {
            existingEmployee.setEmployeeName(employee.getEmployeeName());
        }
        if (employee.getEmail() != null) {
            existingEmployee.setEmail(employee.getEmail());
        }
        if (employee.getDepartment() != null) {
            existingEmployee.setDepartment(employee.getDepartment());
        }
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(int employeeId) {
        Employee employee = employeeList.get(employeeId);

        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            employeeList.remove(employeeId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }
}
