package com.tcs.employeeapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.employeeapplication.dao.OrganizationDAO;
import com.tcs.employeeapplication.model.Department;
import com.tcs.employeeapplication.model.Employee;
import com.tcs.employeeapplication.model.Organization;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	@Autowired
	private OrganizationDAO orgDao;
	
	@Override
	public String addOrganization(Organization organization) {
		// TODO Auto-generated method stub
		return orgDao.addOrganization(organization);
	}

	@Override
	public String updateOrganization(Organization organization) {
		// TODO Auto-generated method stub
		return orgDao.updateOrganization(organization);
	}

	@Override
	public String deleteOrganization(long id) {
		// TODO Auto-generated method stub
		return orgDao.deleteOrganization(id);
	}

	@Override
	public Optional<Organization> findById(long id) {
		// TODO Auto-generated method stub
		return orgDao.findById(id);
	}

	@Override
	public Optional<List<Organization>> getOrganizations() {
		// TODO Auto-generated method stub
		return orgDao.getOrganizations();
	}

	@Override
	public Optional<List<Department>> findDepartmentsByOrganizationId(long id) {
		// TODO Auto-generated method stub
		return orgDao.findDepartmentsByOrganizationId(id);
	}

	@Override
	public Optional<List<Employee>> findEmployeesByOrganizationId(long id) {
		// TODO Auto-generated method stub
		return orgDao.findEmployeesByOrganizationId(id);
	}

}