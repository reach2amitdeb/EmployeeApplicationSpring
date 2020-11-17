package com.tcs.employeeapplication.main;

import java.util.Optional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tcs.employeeapplication.config.DBConfig;
import com.tcs.employeeapplication.model.Employee;
import com.tcs.employeeapplication.service.EmployeeService;

public class EmployeeMain {

	public static void main(String[] args) throws IOException {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DBConfig.class);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String validation;
		char input;
		long empId = 1L;
		do {
			EmployeeService empServices = context.getBean(EmployeeService.class);

			System.out.println("1. Add Employee");
			System.out.println("2. Find Employee By ID");
			System.out.println("3. Update Employee");
			System.out.println("4. Delete Employee");
			System.out.println("5. Get All Employees");
			System.out.println("6. Find Employees by organization ID");
			System.out.println("Enter 0 to exit");

			validation = br.readLine();
			input = (validation.trim().length() == 1 ? validation.charAt(0) : 'x');
			switch (input) {
			case '1':
			// ADD EMPLOYEE
			{
				try {
					Employee employee = new Employee();
					employee.setId(empId);
					System.out.println("Enter Department Id");
					employee.setDepartmentId(Long.parseLong(br.readLine()));
					System.out.println("Enter Organization Id");
					employee.setOrganizationId(Long.parseLong(br.readLine()));
					System.out.println("Enter Name");
					employee.setName(br.readLine());
					System.out.println("Enter Age");
					employee.setAge(Integer.parseInt(br.readLine()));
					System.out.println("Enter Position");
					employee.setPosition(br.readLine());
					String result = empServices.addEmployee(employee);
					if (result.trim().matches("success")) {
						empId++;
					}
					System.out.println("Adding employee : " + result);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				break;
			}
			case '2':
			// FIND EMPLOYEE
			{
				try {
					System.out.println("Enter employeeId");
					Optional<Employee> emp = empServices.findById(Long.parseLong(br.readLine()));

					if (emp.isPresent())
						System.out.println("Present: " + emp.isPresent() + " " + emp.get().toString());
					else
						System.out.println("Present: " + emp.isPresent());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				break;
			}
			case '3':
			// UPDATE EMPLOYEE
			{
				try {
					System.out.println("Please Enter EmployeeId you'd like to update");
					Optional<Employee> emp = empServices.findById(Long.parseLong(br.readLine()));
					Employee employee = emp.get();
					do {
						System.out.println("What would you like to update?");
						System.out.println("Options: Name, age, position.... enter -1 to exit");
						String updateInput = br.readLine();
						switch (updateInput.toLowerCase()) {
						case "name": {
							System.out.print("Name: ");
							employee.setName(br.readLine());
							break;
						}
						case "age": {
							System.out.print("Age: ");
							employee.setAge(Integer.parseInt(br.readLine()));
							break;
						}
						case "position": {
							System.out.print("Position: ");
							employee.setPosition(br.readLine());
							break;
						}
						default: {
							System.out.println("Please Enter A Valid Option");
							break;
						}
						}
					} while (!br.readLine().matches("ok"));
						String result = empServices.updateEmployee(employee);
						System.out.println("Updating Employee : " + result);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				break;
			}

			case '4': {
				// DELETE EMPLOYEE
				System.out.println("Enter employee ID to delete employee");
				String result = empServices.deleteEmployee(Long.parseLong(br.readLine()));
				System.out.println("Deletion : " + result);
			}
			case '5': {
				// GET ALL EMPLOYEES
				Optional<List<Employee>> empList = empServices.getEmployees();
				Stream<Employee> stream = empList.orElse(Collections.emptyList()).stream();
				stream.forEach(s -> System.out.println(s));
				break;
			}
			case '6': {
				// FIND EMPLOYEES BY ORG
				System.out.println("Enter Organization ID");
				Optional<List<Employee>> empList2 = empServices.findByOrganizationId(Long.parseLong(br.readLine()));
				Stream<Employee> stream2 = empList2.orElse(Collections.emptyList()).stream();
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