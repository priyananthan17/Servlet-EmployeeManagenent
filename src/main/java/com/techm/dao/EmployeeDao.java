package com.techm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.techm.model.Employee;
import com.techm.util.DBUtil;
import java.sql.Connection;

public class EmployeeDao {
	public boolean saveEmployee(Employee employee) {
		//Logic to store employee into table ==> insert query
		Connection connection=DBUtil.createConnection();
		String query="insert into Employee values(?,?,?,?,?)";
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setString(1,employee.getEmployeeName());
			statement.setString(2, employee.getEmployeeId());
			statement.setString(3,employee.getDesignation());
			statement.setInt(4, employee.getTotalExperience());
			statement.setDouble(4, employee.getSalary());
			int result=statement.executeUpdate();
			if(result==1) 
			{
				return true;
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean deleteEmployee(int employeeId) {
		//Logic to store employee into table ==> Delete query
		Connection connection =DBUtil.createConnection();
		String query="Delete from Employee where employeeId=?";
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setInt(1, employeeId);
			int result=statement.executeUpdate();
			if(result==1) {
				return true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
				
		return false;
	}
	
	public Employee findEmployee(int employeeId) {
		Connection con=DBUtil.createConnection();
		String query="SELECT * from Employee where employeeId=?";
		try {
			PreparedStatement stat=con.prepareStatement(query);
			stat.setInt(1, employeeId);
			ResultSet result=stat.executeQuery();
			Employee emp=new Employee();
			while(result.next()) {
				emp.setEmployeeName(result.getString("employeeName"));
				emp.setEmployeeId(result.getString("employeeId"));
				emp.setDesignation(result.getString("designation"));
				emp.setSalary(result.getDouble("salary"));
				emp.setTotalExperience(result.getInt("totalExperience"));
				
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Employee> findAllEmployee(){
		Connection con=DBUtil.createConnection();
		String query="SELECT * from Employee";
		try {
			PreparedStatement statement=con.prepareStatement(query);
			ResultSet result=statement.executeQuery();
			Employee emp=new Employee();
			List<Employee> list=new ArrayList<>();
			while(result.next()) {
				emp.setEmployeeName(result.getString("employeeName"));
				emp.setEmployeeId(result.getString("employeeId"));
				emp.setDesignation(result.getString("designation"));
				emp.setSalary(result.getDouble("salary"));
				emp.setTotalExperience(result.getInt("totalExperience"));
				list.add(emp);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Employee updateEmployee(Employee employee) {

		Connection connection = DBUtil.createConnection();

		String query = "UPDATE Employee SET empName=?, designation=?, "

		+ "totalExperience=?, salary=? WHERE empId=?";

		try {

		PreparedStatement statement = connection.prepareStatement(query);

		statement.setString(1, employee.getEmployeeName());

		statement.setString(2, employee.getDesignation());

		statement.setInt(3, employee.getTotalExperience());

		statement.setDouble(4, employee.getSalary());

		statement.setString(5, employee.getEmployeeId());

		int result = statement.executeUpdate();

		if (result == 1) {

		return employee;

		}

		} catch (SQLException e) {

		e.printStackTrace();

		}

		return null;

		}

}