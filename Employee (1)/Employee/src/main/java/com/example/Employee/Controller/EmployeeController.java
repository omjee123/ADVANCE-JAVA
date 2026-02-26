package com.example.Employee.Controller;



import com.example.Employee.Entity.Employee;
import com.example.Employee.Response.Response;
import com.example.Employee.Service.ServiceLayer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final ServiceLayer service;

    public EmployeeController(ServiceLayer service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<Response<Employee>> addEmployee(@RequestBody Employee emp) {
        Employee saved = service.addEmployee(emp);
        Response<Employee> response = new Response<>("Employee added successfully", saved, true);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response<Employee>> getEmployee(@PathVariable int id) {
        Employee emp = service.getEmployee(id);
        Response<Employee> response = new Response<>("Employee found", emp, true);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response<Employee>> updateEmployee(@PathVariable int id, @RequestBody Employee newData) {
        Employee updated = service.updateEmployee(id, newData);
        Response<Employee> response = new Response<>("Employee updated", updated, true);
        return ResponseEntity.ok(response);
    }


}
