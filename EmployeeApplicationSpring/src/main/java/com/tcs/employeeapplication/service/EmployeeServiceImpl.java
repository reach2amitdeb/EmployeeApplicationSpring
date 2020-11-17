package com.tcs.employeeapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.employeeapplication.dao.EmployeeDAO;
import com.tcs.employeeapplication.dao.EmployeeDAOImpl;
import com.tcs.employeeapplication.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDAO employeeDao;
	
	@Override
	public String addEmployee(Employee employee) {
		return employeeDao.addEmployee(employee);
	}

	@Override
	public String updateEmployee(Employee employee) {
		return employeeDao.updateEmployee(employee);
	}

	@Override
	public String deleteEmployee(long id) {
		return employeeDao.deleteEmployee(id);
	}

	@Override
	public Optional<Employee> findById(long id) {
		return employeeDao.findById(id);
	}

	@Override
	public Optional<List<Employee>> getEmployees() {
		return employeeDao.getEmployees();
	}

	@Override
	public Optional<List<Employee>> findByOrganizationId(long id) {
		return employeeDao.findByOrganizationId(id);
	}

}
