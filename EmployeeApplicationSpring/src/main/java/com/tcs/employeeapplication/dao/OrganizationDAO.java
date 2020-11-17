package com.tcs.employeeapplication.dao;

import java.util.List;
import java.util.Optional;

import com.tcs.employeeapplication.model.Department;
import com.tcs.employeeapplication.model.Employee;
import com.tcs.employeeapplication.model.Organization;

public interface OrganizationDAO {
	public String addOrganization(Organization organization);
	public String updateOrganization(Organization organization);
	public String deleteOrganization(long id);
	public Optional<Organization> findById(long id);
	public Optional<List<Organization>> getOrganizations();
	public Optional<List<Department>> findDepartmentsByOrganizationId(long id);
	public Optional<List<Employee>> findEmployeesByOrganizationId(long id);

}
