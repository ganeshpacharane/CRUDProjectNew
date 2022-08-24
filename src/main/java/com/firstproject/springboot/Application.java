package com.firstproject.springboot;

import com.firstproject.springboot.Repository.DepartmentRepository;
import com.firstproject.springboot.Repository.EmployeeRepository;
import com.firstproject.springboot.model.Department;
import com.firstproject.springboot.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public void run(String... args) throws Exception {
		Employee emp = new Employee();
		emp.setFirstName("Ganesh");
		emp.setLastName("Pacharane");
		emp.setEmailId("ganpac@email.com");
		employeeRepository.save(emp);

		Department department = new Department();
		department.setDeptName("IT");
		departmentRepository.save(department);

	}
}
