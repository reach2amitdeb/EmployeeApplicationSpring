package com.tcs.employeeapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.employeeapplication.dao.DepartmentDAO;
import com.tcs.employeeapplication.dao.DepartmentDAOImpl;
import com.tcs.employeeapplication.model.Department;
import com.tcs.employeeapplication.model.Employee;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDAO deptDao;
	
	@Override
	public String addDepartment(Department department) {
		// TODO Auto-generated method stub
		return deptDao.addDepartment(department);
	}

	@Override
	public String updateDepartment(Department department) {
		// TODO Auto-generated method stub
		return deptDao.updateDepartment(department);
	}

	@Override
	public String deleteDepartment(long id) {
		// TODO Auto-generated method stub
		return deptDao.deleteDepartment(id);
	}

	@Override
	public Optional<Department> findById(long id) {
		// TODO Auto-generated method stub
		return deptDao.findById(id);
	}

	@Override
	public Optional<List<Department>> getDepartments() {
		// TODO Auto-generated method stub
		return deptDao.getDepartments();
	}

	@Override
	public Optional<List<Employee>> findEmployeesByDepartmentId(long id) {
		// TODO Auto-generated method stub
		return deptDao.findEmployeesByDepartmentId(id);
	}

}
