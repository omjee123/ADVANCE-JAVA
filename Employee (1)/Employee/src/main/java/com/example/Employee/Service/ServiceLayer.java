package com.example.Employee.Service;

import com.example.Employee.Entity.Employee;
import com.example.Employee.Exception.EmployeeNotFoundException;
import com.example.Employee.Exception.InvalidEmpolyeeDataException;
import com.example.Employee.Repository.EmployeRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServiceLayer {
    private final EmployeRepository repo;

    public ServiceLayer(EmployeRepository employee) {
        this.repo = employee;
    }


    public Employee addEmployee(Employee employee) {

        if (employee == null) {
            throw new InvalidEmpolyeeDataException("Employee data cannot be null");
        }

        if (employee.getName() == null || employee.getName().isBlank()) {
            throw new InvalidEmpolyeeDataException("Employee name is required");
        }

        if (employee.getSalary() <= 0) {
            throw new InvalidEmpolyeeDataException("Employee salary must be greater than zero");
        }

        if (employee.getDepartment() == null || employee.getDepartment().isBlank()) {
            throw new InvalidEmpolyeeDataException("Employee department is required");
        }

        return repo.save(employee);
    }

    public Employee getEmployee(int id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee not found with id: " + id));
    }


    public Employee updateEmployee(int id, Employee newData) {

        if (newData == null) {
            throw new InvalidEmpolyeeDataException("Updated employee data cannot be null");
        }

        Employee employee = repo.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee not found with id: " + id));

        if (newData.getName() != null && !newData.getName().isBlank()) {
            employee.setName(newData.getName());
        }

        if (newData.getSalary() > 0) {
            employee.setSalary(newData.getSalary());
        }

        if (newData.getDepartment() != null && !newData.getDepartment().isBlank()) {
            employee.setDepartment(newData.getDepartment());
        }

        return repo.save(employee);
    }


    public void deleteEmployee(int id) {
        if (!repo.existsById(id)) {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
        repo.deleteById(id);
    }


    public List<Employee> getAllStudents() {
        return repo.findAll();
    }

    public List<Employee> getAllEmployee() {
        return List.of();
    }
}
