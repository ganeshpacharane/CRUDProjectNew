package com.firstproject.springboot.Controller;

import com.firstproject.springboot.Exception.ResourceNotFoundException;
import com.firstproject.springboot.Repository.EmployeeRepository;
import com.firstproject.springboot.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee emp){
                  return employeeRepository.save(emp);
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found id"+ id));

        return ResponseEntity.ok(employee);

    }


    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable long id , @RequestBody Employee emp){
        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exist id"+ id));

        updateEmployee.setFirstName(emp.getFirstName());
        updateEmployee.setLastName(emp.getLastName());
        updateEmployee.setEmailId(emp.getEmailId());

        employeeRepository.save(updateEmployee);
        return ResponseEntity.ok(updateEmployee);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){
        Employee employee = employeeRepository.findById(id)

                .orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id"+ id));

                  employeeRepository.delete(employee);

                  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
