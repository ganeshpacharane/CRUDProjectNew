package com.firstproject.springboot.Controller;

import com.firstproject.springboot.Exception.ResourceNotFoundException;
import com.firstproject.springboot.Repository.DepartmentRepository;
import com.firstproject.springboot.model.Department;
import com.firstproject.springboot.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v2/department")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping
    public List<Department> getDepartmentDetails(){ return departmentRepository.findAll();}

    @PostMapping
    public Department addDepartmentDetails(@RequestBody Department dmp){ return departmentRepository.save(dmp);}

    @GetMapping("{DeptId}")
    public ResponseEntity<Department> getDepartmentDetailsBYID(@PathVariable long DeptId){
        Department department = departmentRepository.findById(DeptId)
                .orElseThrow(()-> new ResourceNotFoundException("Department not found with id"+ DeptId));

        return ResponseEntity.ok(department);
    }

    @DeleteMapping("{DeptId}")
    public ResponseEntity<HttpStatus> deleteDepartmentById(@PathVariable long DeptId){
        Department department = departmentRepository.findById(DeptId)
                .orElseThrow(()-> new ResourceNotFoundException("Department not found with id"+DeptId));

        departmentRepository.deleteById(DeptId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{DeptId}")
    public ResponseEntity<Department> updateDepartmentDetails(@PathVariable long DeptId,@RequestBody Department dp){
        Department department = departmentRepository.findById(DeptId)
                .orElseThrow(()-> new ResourceNotFoundException("Department not found with id"+DeptId));

        department.setDeptName(dp.getDeptName());

        departmentRepository.save(department);

        return ResponseEntity.ok(department);

    }
}
