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
import com.tcs.employeeapplication.model.Organization;
import com.tcs.employeeapplication.service.EmployeeService;
import com.tcs.employeeapplication.service.OrganizationService;
import com.tcs.employeeapplication.service.OrganizationServiceImpl;

public class OrganizationMain {
	 public static void main(String[] args)throws IOException {
			// TODO Auto-generated method stub
		 	
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DBConfig.class);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String validation;
			char input;
			long orgId = 1L;
			do {
				OrganizationService orgServices = context.getBean(OrganizationService.class);

				System.out.println("1. Add Organization");
				System.out.println("2. Find Organization By ID");
				System.out.println("3. Update Organization");
				System.out.println("4. Delete Organization");
				System.out.println("5. Get All Organizations");
				System.out.println("6. Find Departments by organization ID");
				System.out.println("7. Find Employees by organization ID");
				System.out.println("Enter 0 to exit");

				validation = br.readLine();
				input = (validation.trim().length() == 1 ? validation.charAt(0) : 'x');
				switch (input) {
				case '1':
				// ADD Organization
				{
					try {
						Organization organization = new Organization();
						organization.setId(orgId);
						System.out.println("Enter Organization Id");
						organization.setId(Long.parseLong(br.readLine()));
						System.out.println("Enter Name");
						organization.setName(br.readLine());
						System.out.println("Enter Organization Address");
						organization.setAddress(br.readLine());
						String result = orgServices.addOrganization(organization);

						if (result.trim().matches("success")) {
							orgId++;
						}
						System.out.println("Adding Organization : " + result);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
					break;
				}
				case '2':
				// FIND Organization
				{
					try {
						System.out.println("Enter OrganizationId");
						Optional<Organization> org = orgServices.findById(Long.parseLong(br.readLine()));

						if (org.isPresent())
							System.out.println("Present: " + org.isPresent() + " " + org.get().toString());
						else
							System.out.println("Present: " + org.isPresent());
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
					break;
				}
				case '3':
				// UPDATE Organization
				{
					try {
						System.out.println("Please Enter OrganizationId you'd like to update");
						Optional<Organization> org = orgServices.findById(Long.parseLong(br.readLine()));
						Organization Organization = org.get();
						do {
							System.out.println("What would you like to update?");
							System.out.println("Options: Name, age, position.... enter -1 to exit");
							String updateInput = br.readLine();
							switch (updateInput.toLowerCase()) {
							case "name": {
								System.out.print("Name: ");
								Organization.setName(br.readLine());
								break;
							}
							case "address": {
								System.out.print("Address: ");
								Organization.setAddress(br.readLine());
								break;
							}
							default: {
								System.out.println("Please Enter A Valid Option");
								break;
							}
							}
						} while (!br.readLine().matches("-1"));
						String result = orgServices.updateOrganization(Organization);
						System.out.println("Updating Organization : " + result);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
					break;
				}

				case '4': {
					// DELETE Organization
					System.out.println("Enter Organization ID to delete Organization");
					String result = orgServices.deleteOrganization(Long.parseLong(br.readLine()));
					System.out.println("Deletion : " + result);
				}
				case '5': {
					// GET ALL OrganizationS
					Optional<List<Organization>> orgList = orgServices.getOrganizations();
					Stream<Organization> stream = orgList.orElse(Collections.emptyList()).stream();
					stream.forEach(s -> System.out.println(s));
					break;
				}
				case '6': {
					// FIND Department BY ORG
					System.out.println("Enter Organization ID");
					Optional<List<Department>> orgList2 = orgServices.findDepartmentsByOrganizationId(Long.parseLong(br.readLine()));
					Stream<Department> stream2 = orgList2.orElse(Collections.emptyList()).stream();
					stream2.forEach(s -> System.out.println(s));
					break;
				}
				case '7': {
					// FIND Department BY ORG
					System.out.println("Enter Organization ID");
					Optional<List<Employee>> orgList2 = orgServices.findEmployeesByOrganizationId(Long.parseLong(br.readLine()));
					Stream<Employee> stream2 = orgList2.orElse(Collections.emptyList()).stream();
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
