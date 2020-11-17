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
import com.tcs.employeeapplication.utils.DBUtils;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

	@Autowired
	private DBUtils dbUtils;

	@Override
	public String addDepartment(Department department) {
		// TODO Auto-generated method stub
		Connection connection = dbUtils.getConnection();
		String insertDepartment = "insert into DEPARTMENT values(?,?,?)";
		PreparedStatement preparedStmt = null;
		int result = 0;
		try {
			preparedStmt = connection.prepareStatement(insertDepartment);
			preparedStmt.setLong(1, department.getId());
			preparedStmt.setLong(2, department.getOrganizationId());
			preparedStmt.setString(3, department.getName());

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
	public String updateDepartment(Department department) {
		// TODO Auto-generated method stub
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStmt = null;
		int result = 0;
		String update = "update DEPARTMENT set departmentid=?, "
				+ "organizationid=?, departmentname=? where departmentid=" + department.getId();
		try {
			preparedStmt = connection.prepareStatement(update);
			preparedStmt.setLong(1, department.getId());
			preparedStmt.setLong(2, department.getOrganizationId());
			preparedStmt.setString(3, department.getName());

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
	public String deleteDepartment(long id) {
		// TODO Auto-generated method stub
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStmt = null;
		int result = 0;
		String query = "delete from DEPARTMENT where departmentid=?";
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
	public Optional<Department> findById(long id) {
		// TODO Auto-generated method stub
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStmt = null;
		ResultSet resultSet = null;

		Department department = null;
		String query = "select * from DEPARTMENT where departmentid=?";
		try {
			department = new Department();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setLong(1, id);

			resultSet = preparedStmt.executeQuery();

			if (resultSet.next()) {
				department.setId(resultSet.getLong("departmentid"));
				department.setOrganizationId(resultSet.getLong("organizationid"));
				department.setName(resultSet.getString("departmentname"));

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
		return Optional.of(department);
	}

	@Override
	public Optional<List<Department>> getDepartments() {
		// TODO Auto-generated method stub
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStmt = null;
		ResultSet resultSet = null;

		List<Department> deptList = null;
		String query = "select * from DEPARTMENT";
		try {
			preparedStmt = connection.prepareStatement(query);
			resultSet = preparedStmt.executeQuery();
			deptList = new ArrayList<Department>();

			while (resultSet.next()) {
				Department department = new Department();
				department.setId(resultSet.getLong("departmentid"));
				department.setOrganizationId(resultSet.getLong("organizationid"));
				department.setName(resultSet.getString("departmentname"));
				deptList.add(department);
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
		if (deptList.isEmpty())
			deptList = null;
		return Optional.of(deptList);
	}

	@Override
	public Optional<List<Employee>> findEmployeesByDepartmentId(long id) {
		// TODO Auto-generated method stub
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStmt = null;
		ResultSet resultSet = null;

		List<Employee> employeeList = null;
		String query = "select *  from EMPLOYEE where departmentid=?";
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