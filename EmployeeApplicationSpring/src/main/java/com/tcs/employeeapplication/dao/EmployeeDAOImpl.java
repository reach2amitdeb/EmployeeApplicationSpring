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

import com.tcs.employeeapplication.model.Employee;
import com.tcs.employeeapplication.utils.DBUtils;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private DBUtils dbUtils;

	@Override
	public String addEmployee(Employee employee) {
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStatement = null;
		int result = 0;
		String addEmployee = "INSERT INTO Employee (id, organizationId, departmentId, name, age, position) VALUES(?,?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(addEmployee);
			preparedStatement.setLong(1, employee.getId());
			preparedStatement.setLong(2, employee.getOrganizationId());
			preparedStatement.setLong(3, employee.getDepartmentId());
			preparedStatement.setString(4, employee.getName());
			preparedStatement.setInt(5, employee.getAge());
			preparedStatement.setString(6, employee.getPosition());
		
			result = preparedStatement.executeUpdate();
						
			if(result > 0) {
				connection.commit();
				return "success";
			}else {
				return "fail";
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return "fail";
		}finally {
			dbUtils.closeConnection(connection);
		}
	}

	@Override
	public String updateEmployee(Employee employee) {
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStmt = null;
		int result = 0;
		String update = "UPDATE EMPLOYEE SET id=?, organizationId=?, "
				+ "departmentId=?, name=?, age=?, position=? WHERE id=" + employee.getId();
			try {
				preparedStmt = connection.prepareStatement(update);
				 preparedStmt.setLong(1, employee.getId());
				 preparedStmt.setLong(2, employee.getOrganizationId());
				 preparedStmt.setLong(3, employee.getDepartmentId());
				 preparedStmt.setString(4, employee.getName());
				 preparedStmt.setInt(5, employee.getAge());
				 preparedStmt.setString(6, employee.getPosition());
				 
				 result = preparedStmt.executeUpdate();
			 if(result > 0)
			 {
				 connection.commit();
				 return "success";
			 }
			 else		 		
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
	public String deleteEmployee(long id) {
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStatement = null;
		int result = 0;		
		String deleteEmployee = "DELETE FROM Employee WHERE id=?";

		try {
			preparedStatement = connection.prepareStatement(deleteEmployee);
			preparedStatement.setLong(1, id);
					
			result = preparedStatement.executeUpdate();
					
			if(result > 0) {
				connection.commit();
				return "success";
			}else {
				return "fail";
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return "fail";
		}finally {
			dbUtils.closeConnection(connection);
		}
	}

	@Override
	public Optional<Employee> findById(long id) {
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStmt = null;
		ResultSet resultSet = null;
		
		Employee employee = null;
		String query = "select * from EMPLOYEE where id=?";
			try {
			 employee = new Employee();
			 preparedStmt = connection.prepareStatement(query);
			 preparedStmt.setLong(1, id);
			 
			 resultSet = preparedStmt.executeQuery();
			 
			 if(resultSet.next()) {
			 employee.setId(resultSet.getLong("id"));
			 employee.setOrganizationId(resultSet.getLong("organizationId"));
			 employee.setDepartmentId(resultSet.getLong("departmentId"));
			 employee.setName(resultSet.getString("name"));
			 employee.setAge(resultSet.getInt("age"));
			 employee.setPosition(resultSet.getString("position"));
			 }
			} catch (SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				return Optional.empty();
			} finally {
				dbUtils.closeConnection(connection);
			}
		return Optional.of(employee);
	}

	@Override
	public Optional<List<Employee>> getEmployees() {
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStmt = null;
		ResultSet resultSet = null;
		
		List<Employee> employeeList = null;
		String query = "select *  from EMPLOYEE";
			try {
			 employeeList = new ArrayList<Employee>();
			 preparedStmt = connection.prepareStatement(query);
			 resultSet = preparedStmt.executeQuery();
			 
			 while(resultSet.next()) {
			 Employee employee = new Employee();
			 employee.setId(resultSet.getLong("id"));
			 employee.setOrganizationId(resultSet.getLong("organizationId"));
			 employee.setDepartmentId(resultSet.getLong("departmentId"));
			 employee.setName(resultSet.getString("name"));
			 employee.setAge(resultSet.getInt("age"));
			 employee.setPosition(resultSet.getString("position"));
			 
			 employeeList.add(employee);			 }
			} catch (SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				return Optional.empty();
			} finally {
				dbUtils.closeConnection(connection);
			}
		return Optional.of(employeeList);
	}

	@Override
	public Optional<List<Employee>> findByOrganizationId(long id) {
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStmt = null;
		ResultSet resultSet = null;
		
		List<Employee> employeeList = null;
		String query = "select *  from EMPLOYEE where organizationId=?";
			try {
			 employeeList = new ArrayList<Employee>();
			 preparedStmt = connection.prepareStatement(query);
			 preparedStmt.setLong(1, id);
			 resultSet = preparedStmt.executeQuery();
			 
			 while(resultSet.next()) {
			 Employee employee = new Employee();
			 employee.setId(resultSet.getLong("id"));
			 employee.setOrganizationId(resultSet.getLong("organizationId"));
			 employee.setDepartmentId(resultSet.getLong("departmentId"));
			 employee.setName(resultSet.getString("name"));
			 employee.setAge(resultSet.getInt("age"));
			 employee.setPosition(resultSet.getString("position"));
			 
			 employeeList.add(employee);			 }
			} catch (SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
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
