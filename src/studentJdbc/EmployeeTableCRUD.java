package studentJdbc;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
 import java.sql.SQLException;
import java.util.Scanner;
public class EmployeeTableCRUD {
	static Connection con;
	public static void connect()throws Exception{
	Class.forName("com.mysql.cj.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Testdb","root","");
}
	//insert value
	public static void insertEmployee() throws Exception{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Employee id: ");
		int id=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Employee name: ");
		String name=sc.next();
		sc.nextLine();
		System.out.println("Enter Employee salary: ");
		int salary=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Employee department: ");
		String dept=sc.next();
		sc.nextLine();
		PreparedStatement ps=con.prepareStatement("INSERT into employee VALUES(?,?,?,?)");
		ps.setInt(1, id);
		ps.setString(2,name);
		ps.setInt(3, salary);
		ps.setString(4, dept);
		int rows=ps.executeUpdate();
	     System.out.println(rows + "Employee record inserted."); 
		
	}
	//read value or view
	public static void reademployee() throws Exception{
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("SELECT * from employee");
		System.out.println("---Employee list---");
		while (rs.next())//move to next row {
		System.out.println("ID: " + rs.getInt("emp_id") + " , Name: " +rs.getString("emp_name") + ",Salary: "+ rs.getInt("emp_salary") + ",Department: "+ rs.getString("emp_dept"));
	}
	//Update value
	public static void updateemployee() throws Exception{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Employee id to update: ");
		int id=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Employee new name: ");
		String name=sc.next();
		sc.nextLine();
		System.out.println("Enter Employee salary to update: ");
		int salary=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Employee department to update:  ");
		String dept=sc.next();
		sc.nextLine();
		PreparedStatement ps=con.prepareStatement("UPDATE employee  SET emp_name=?,emp_salary=?,emp_dept=? where emp_id=?");
		ps.setString(1, name);
	    ps.setInt(2, salary);
	    ps.setString(3, dept);
		ps.setInt(4, id);
		int rows1=ps.executeUpdate();
		System.out.println(rows1 + "Employee record updated");
		 
	}
	//delete record
	public static void deleteemployee() throws Exception {
		 Scanner sc=new Scanner(System.in);
		 System.out.println("Enter Employee id to delte: ");
		 int id=sc.nextInt();
		 sc.nextLine();
		 PreparedStatement ps=con.prepareStatement("Delete from employee where emp_id=? ");
		 ps.setInt(1, id);
		 int rows=ps.executeUpdate();
		 System.out.println(rows + "Employee record deleted.");
		 
	 }
	public static void main(String[] args) throws Exception {
		connect();
		Scanner sc=new Scanner(System.in);
		int choice;
		do {
		
			System.out.println("------Employye table operations-----");
			System.out.println("1.Insert employee");
			System.out.println("2.Read employee");
	        System.out.println("3.Update employee");
	        System.out.println("4.Delete employee");
	        System.out.println("5.Exit");
	        System.out.println("Enter your choice--> ");
	        choice=sc.nextInt();
	        
	        switch(choice) {
	        case 1:
	        	insertEmployee();
	        	break;
	        case 2:
	        	reademployee();
	        	break;
	        case 3:
	        	updateemployee();
	        	break;
	        case 4:
	        	deleteemployee();
	        	break;
	        case 5:
	        	System.out.println("Existing....");
	        	break;
	        	default:
	        		System.out.println("Invalid choice.");
	        }
	     } while(choice !=5);
	        con.close();
	}
}



		
	


