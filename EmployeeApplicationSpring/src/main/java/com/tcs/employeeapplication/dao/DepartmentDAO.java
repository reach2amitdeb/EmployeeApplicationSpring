package com.tcs.employeeapplication.dao;

import java.util.List;
import java.util.Optional;

import com.tcs.employeeapplication.model.Department;
import com.tcs.employeeapplication.model.Employee;

public interface DepartmentDAO {
	public String addDepartment(Department department);
	public String updateDepartment(Department department);
	public String deleteDepartment(long id);
	public Optional<Department> findById(long id);
	public Optional<List<Department>> getDepartments();
	public Optional<List<Employee>> findEmployeesByDepartmentId(long id);
}
