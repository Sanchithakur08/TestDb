package SwingsTest;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.List;
import java.util.Scanner;
import javax.swing.JList;
public class employeeCRUD {
		//CREATE
	public void createEmployee(employeeDatabase emp) throws Exception{
		String sql="INSERT into employee(emp_id,emp_name,emp_salary,emp_dept) values(?,?,?,?)";
		Connection con=DBconnection.getConnection();
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1,emp.getEmp_id());
		ps.setString(2,emp.getEmp_name());
		ps.setInt(3,emp.getEmp_salary());
		ps.setString(4,emp.getEmp_dept());
		int rows=ps.executeUpdate();
		System.out.println(rows + "employee inserted.");
		ps.close();
		con.close();
   }
	//READ
	public List<employeeDatabase>getAllEmployees()throws Exception{
		List<employeeDatabase>emplist=new ArrayList<>();
		
		
		try {
		Connection con=DBconnection.getConnection();
		String sql="SELECT * from employee ";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				employeeDatabase emp = new employeeDatabase();
				emp.setEmp_id(rs.getInt("emp_id"));
				emp.setEmp_name(rs.getString("emp_name"));
				emp.setEmp_salary(rs.getInt("emp_salary"));
				emp.setEmp_dept(rs.getString("emp_dept"));
				emplist.add(emp);
			}
		}
		catch(Exception e) {
			
			System.out.println("Error reading employees: "+ e.getMessage());
		}
		return emplist;
}
		
	//update
	public void updateEmployee(employeeDatabase emp) throws Exception {
		String sql="UPDATE employee SET emp_name=?, emp_salary=?, emp_dept=? where emp_id=?";
		Connection con=DBconnection.getConnection();
		PreparedStatement ps=con.prepareStatement(sql);
		
		ps.setString(1,emp.getEmp_name());
		ps.setInt(2,emp.getEmp_salary());
		ps.setString(3,emp.getEmp_dept());
		ps.setInt(4,emp.getEmp_id());
		int rows=ps.executeUpdate();
		System.out.println(rows + "employee updated.");
		ps.close();
		con.close();
}
	//delete
	public void deleteEmployee(int id) throws Exception {
		String sql = "DELETE FROM employee WHERE emp_id=?";
		Connection con=DBconnection.getConnection();
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1,id);
		int rows =ps.executeUpdate();
		System.out.println(rows + "Employee deleted.");
		ps.close();
		con.close();
		
		
	}
	
	
}
