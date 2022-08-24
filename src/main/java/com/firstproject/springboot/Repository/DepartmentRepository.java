package com.firstproject.springboot.Repository;

import com.firstproject.springboot.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

}
