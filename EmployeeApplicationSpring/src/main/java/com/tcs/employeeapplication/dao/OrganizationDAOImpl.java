package com.tcs.employeeapplication.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tcs.employeeapplication.model.Department;
import com.tcs.employeeapplication.model.Employee;
import com.tcs.employeeapplication.model.Organization;
import com.tcs.employeeapplication.utils.DBUtils;

@Repository
public class OrganizationDAOImpl implements OrganizationDAO {
	
	@Autowired
	DBUtils dbUtils;
	
	@Override
	public String addOrganization(Organization organization) {
		// TODO Auto-generated method stub
		Connection connection = dbUtils.getConnection();
		String insertOrganization = "insert into ORGANIZATION values(?,?,?)";
		PreparedStatement preparedStmt = null;
		int result = 0;
		try {
			preparedStmt = connection.prepareStatement(insertOrganization);
			preparedStmt.setLong(1, organization.getId());
			preparedStmt.setString(2, organization.getName());
			preparedStmt.setString(3, organization.getAddress());

			result = preparedStmt.executeUpdate();

			if (result > 0) {
				connection.commit();
				return "success";
			} else
				return "problem";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return "problem";
		} finally {
			dbUtils.closeConnection(connection);
		}
	}

	@Override
	public String updateOrganization(Organization organization) {
		// TODO Auto-generated method stub
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStmt = null;
		int result = 0;
		String update = "update ORGANIZATION set organizationid=?, "
				+ "organizationname=?, organizationaddress=? where organizationid=" + organization.getId();
		try {
			preparedStmt = connection.prepareStatement(update);
			preparedStmt.setLong(1, organization.getId());
			preparedStmt.setString(2, organization.getName());
			preparedStmt.setString(3, organization.getAddress());

			result = preparedStmt.executeUpdate();
			if (result > 0) {
				connection.commit();
				return "success";
			} else
				return "problem";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return "problem";
		} finally {
			dbUtils.closeConnection(connection);
		}
	}

	@Override
	public String deleteOrganization(long id) {
		// TODO Auto-generated method stub
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStmt = null;
		int result = 0;
		String query = "delete from ORGANIZATION where organizationid=?";
		try {
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setLong(1, id);

			result = preparedStmt.executeUpdate();

			if (result > 0) {
				connection.commit();
				return "success";
			} else
				return "problem";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return "problem";
		} finally {
			dbUtils.closeConnection(connection);
		}
	}

	@Override
	public Optional<Organization> findById(long id) {
		// TODO Auto-generated method stub
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStmt = null;
		ResultSet resultSet = null;

		Organization organization = null;
		String query = "select * from ORGANIZATION where organizationid=?";
		try {
			organization = new Organization();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setLong(1, id);

			resultSet = preparedStmt.executeQuery();

			if (resultSet.next()) {
				organization.setId(resultSet.getLong("organizationid"));
				organization.setName(resultSet.getString("organizationname"));
				organization.setAddress(resultSet.getString("organizationaddress"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return Optional.empty();
		} finally {
			dbUtils.closeConnection(connection);
		}
		return Optional.of(organization);
	}

	@Override
	public Optional<List<Organization>> getOrganizations() {
		// TODO Auto-generated method stub
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStmt = null;
		ResultSet resultSet = null;

		List<Organization> orgList = null;
		String query = "select * from ORGANIZATION";
		try {
			preparedStmt = connection.prepareStatement(query);
			resultSet = preparedStmt.executeQuery();
			orgList = new ArrayList<Organization>();

			while (resultSet.next()) {
				Organization organization = new Organization();
				organization.setId(resultSet.getLong("organizationid"));
				organization.setName(resultSet.getString("organizationname"));
				organization.setAddress(resultSet.getString("organizationaddress"));
				orgList.add(organization);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return Optional.empty();
		} finally {
			dbUtils.closeConnection(connection);
		}
		if (orgList.isEmpty())
			orgList = null;
		return Optional.of(orgList);
	}

	@Override
	public Optional<List<Department>> findDepartmentsByOrganizationId(long id) {
		// TODO Auto-generated method stub
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStmt = null;
		ResultSet resultSet = null;

		List<Department> departmentList = null;
		String query = "select *  from DEPARTMENT where organizationid=?";
		try {
			departmentList = new ArrayList<Department>();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setLong(1, id);
			resultSet = preparedStmt.executeQuery();

			while (resultSet.next()) {
				Department department = new Department();
				department.setId(resultSet.getLong("departmentid"));
				department.setOrganizationId(resultSet.getLong("organizationid"));
				department.setName(resultSet.getString("departmentname"));

				departmentList.add(department);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return Optional.empty();
		} finally {
			dbUtils.closeConnection(connection);
		}
		return Optional.of(departmentList);
	}

	@Override
	public Optional<List<Employee>> findEmployeesByOrganizationId(long id) {
		// TODO Auto-generated method stub
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStmt = null;
		ResultSet resultSet = null;

		List<Employee> employeeList = null;
		String query = "select *  from EMPLOYEE where organizationid=?";
		try {
			employeeList = new ArrayList<Employee>();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setLong(1, id);
			resultSet = preparedStmt.executeQuery();

			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getLong("employeeid"));
				employee.setOrganizationId(resultSet.getLong("organizationid"));
				employee.setDepartmentId(resultSet.getLong("departmentid"));
				employee.setName(resultSet.getString("name"));
				employee.setAge(resultSet.getInt("age"));
				employee.setPosition(resultSet.getString("position"));

				employeeList.add(employee);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return Optional.empty();
		} finally {
			dbUtils.closeConnection(connection);
		}
		return Optional.of(employeeList);

	}

}