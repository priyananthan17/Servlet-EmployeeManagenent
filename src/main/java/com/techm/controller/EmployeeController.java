package com.techm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techm.dao.EmployeeDao;
import com.techm.model.Employee;

@WebServlet("/EmployeeController")
public class EmployeeController extends HttpServlet {
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
			//1. Set response type
			response.setContentType("text/html");
			//2. Create Output Stream
			PrintWriter out=response.getWriter();
			//3. Read Form data
			String name=request.getParameter("employeeName");
			String id=request.getParameter("employeeId");
			double salary=Double.parseDouble(request.getParameter("salary"));
			String designation=request.getParameter("designation");
			int experience=Integer.parseInt(request.getParameter("totalExperience"));
			String option=request.getParameter("options");
			System.out.println(option);
			if(option=="Save") {
			//3. Create Employee Object & Assign Form data
			Employee employee=new Employee();
			employee.setDesignation(designation);
			employee.setEmployeeId(id);
			employee.setEmployeeName(name);
			employee.setSalary(salary);
			employee.setTotalExperience(experience);
			//4. Create Object for DAO class and call methods
			EmployeeDao employeeDao=new EmployeeDao();
			boolean result=employeeDao.saveEmployee(employee);
			if(result) {
				out.write("<h3>Employee Store Successfully</h3>");
				ArrayList<Employee> al=employeeDao.findAllEmployee();
				out.print("<table border='1' width='100%'");  
		        out.print("<tr><th>Id</th><th>Name</th><th>Designation</th><th>Salary</th><th>Experience(In Years)</th><th>Edit</th><th>Delete</th></tr>");  
		         for(Employee emp:al){      
		         out.print("<tr><td>"+emp.getEmployeeId()+"</td><td>"+emp.getEmployeeName()+"</td><td>"+emp.getDesignation()+"</td><td>"+emp.getSalary()+"</td><td>"+emp.getTotalExperience()+"</td><td><a href='EditEmployee?id="+emp.getEmployeeId()+"'>edit</a></td><td><a href='DeleteEmployee?id="+emp.getEmployeeId()+"'>delete</a></td></tr>"); 
		        out.print("</table>");  
			}}
			else {
				out.write("Error in Storing Employee Data");
				out.write("<a href='register.html'>Home Page</a>");
			}
			}
			
	}

}
