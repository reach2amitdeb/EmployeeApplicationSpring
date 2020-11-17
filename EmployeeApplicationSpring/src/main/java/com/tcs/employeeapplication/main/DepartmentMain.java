package com.tcs.employeeapplication.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tcs.employeeapplication.config.DBConfig;
import com.tcs.employeeapplication.model.Department;
import com.tcs.employeeapplication.model.Employee;
import com.tcs.employeeapplication.service.DepartmentService;

public class DepartmentMain {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DBConfig.class);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String validation;
		char input;
		long deptId = 1L;
		do {
			DepartmentService deptServices = context.getBean(DepartmentService.class);

			System.out.println("1. Add Department");
			System.out.println("2. Find Department By ID");
			System.out.println("3. Update Department");
			System.out.println("4. Delete Department");
			System.out.println("5. Get All Departments");
			System.out.println("6. Find Employees by Department ID");
			System.out.println("Enter 0 to exit");

			validation = br.readLine();
			input = (validation.trim().length() == 1 ? validation.charAt(0) : 'x');
			switch (input) {
			case '1':
			// ADD Department
			{
				try {
					Department department = new Department();
					department.setId(deptId);
					System.out.println("Enter Department Id");
					department.setId(Long.parseLong(br.readLine()));
					System.out.println("Enter Organization Id");
					department.setOrganizationId(Long.parseLong(br.readLine()));
					System.out.println("Enter Name");
					department.setName(br.readLine());
					String result = deptServices.addDepartment(department);
					if (result.trim().matches("success")) {
						deptId++;
					}
					System.out.println("Adding Department : " + result);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				break;
			}
			case '2':
			// FIND Department
			{
				try {
					System.out.println("Enter DepartmentId");
					Optional<Department> dept = deptServices.findById(Long.parseLong(br.readLine()));

					if (dept.isPresent())
						System.out.println("Present: " + dept.isPresent() + " " + dept.get().toString());
					else
						System.out.println("Present: " + dept.isPresent());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				break;
			}
			case '3':
			// UPDATE Department
			{
				try {
					System.out.println("Please Enter DepartmentId you'd like to update");
					Optional<Department> dept = deptServices.findById(Long.parseLong(br.readLine()));
					Department department = dept.get();
					System.out.println("What is you new department name?");
					String updateInput = br.readLine();
					System.out.print("Name: ");
					department.setName(br.readLine());
							
					String result = deptServices.updateDepartment(department);
					System.out.println("Updating Department : " + result);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				break;
			}

			case '4': {
				// DELETE Department
				System.out.println("Enter Department ID to delete Department");
				String result = deptServices.deleteDepartment(Long.parseLong(br.readLine()));
				System.out.println("Deletion : " + result);
			}
			case '5': {
				// GET ALL DepartmentS
				Optional<List<Department>> deptList = deptServices.getDepartments();
				Stream<Department> stream = deptList.orElse(Collections.emptyList()).stream();
				stream.forEach(s -> System.out.println(s));
				break;
			}
			case '6': {
				// FIND DepartmentS BY ORG
				System.out.println("Enter Department ID");
				Optional<List<Employee>> deptList2 = deptServices.findEmployeesByDepartmentId(Long.parseLong(br.readLine()));
				Stream<Employee> stream2 = deptList2.orElse(Collections.emptyList()).stream();
				stream2.forEach(s -> System.out.println(s));
				break;
			}
			default:
				System.out.println("Please Enter a Valid Input (ignore if exiting)");
				break;
			}
		} while (input != '0');

		System.out.println("System has exited...");
		context.close();

	}
}
