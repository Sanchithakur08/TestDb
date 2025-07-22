package studentJdbc;
import java.sql.*;
import java.util.Scanner;
public class CRUD {
	static Connection con;
	public static void connect()throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
	    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Testdb","root","");
	}
	//Insert values
	public static void InsertValue() throws Exception{
		 Scanner sc=new Scanner(System.in);
		 System.out.println("Enter id: ");
		 int id=sc.nextInt();
		 sc.nextLine();
		 
		 System.out.println("Enter name: ");
		 String name=sc.nextLine();
		// sc.nextLine();
		 
		 PreparedStatement ps=con.prepareStatement("INSERT into student VALUES(?,?)");
		 ps.setInt(1, id);
		 ps.setString(2,name);
		 int rows=ps.executeUpdate();
		 System.out.println(rows +" student record inserted."); 
	 }
	 //Read value
	 public static void Readvalue() throws Exception{
		 Statement stmt=con.createStatement();
		 ResultSet rs=stmt.executeQuery("SELECT * from student");
		 System.out.println("Student List: ");
		 while (rs.next())//move to next row {
		 System.out.println("ID: " + rs.getInt("id") + " , Name: " +rs.getString("name"));
		 }

	 //Update value
	 public static void Updatevalue() throws Exception {
		 Scanner sc=new Scanner(System.in);
		 //update id
		 System.out.println("Enter id to update: ");
		 int id=sc.nextInt();
		 sc.nextLine();
		 //update name
		 System.out.println("enter name to update: ");
		 String name=sc.next();
		 sc.nextLine();//clear the buffer
		 
		 PreparedStatement ps=con.prepareStatement("UPDATE student  SET name=? where id=? ");
		 ps.setString(1, name);
		 ps.setInt(2, id);
		 int rows=ps.executeUpdate();
		 System.out.println(rows + "Student record updated");
		 
	 }
	 //delete value
	 public static void Deletevalue() throws Exception {
		 Scanner sc=new Scanner(System.in);
		 System.out.println("Enter id to delte: ");
		 int id=sc.nextInt();
		 sc.nextLine();
		 PreparedStatement ps=con.prepareStatement("Delete from student where id=? ");
		 ps.setInt(1, id);
		 int rows=ps.executeUpdate();
		 System.out.println(rows + "Student record deleted.");
		 
	 }
	public static void main(String[] args) throws Exception {
			connect();
			Scanner sc=new Scanner(System.in);
			int choice;
			do {
				System.out.println("------CRUD menu-----");
				System.out.println("1.Insert student");
				System.out.println("2.Read student");
		        System.out.println("3.Update student");
		        System.out.println("4.Delete student");
		        System.out.println("5.Exit");
		        System.out.println("Enter your choice--> ");
		        
		        choice=sc.nextInt();
		        switch(choice) {
		        case 1:
		        	InsertValue();
		        		break;
		        case 2:
		        	Readvalue();
		        	break;
		        case 3:
		        	Updatevalue();
		        	break;
		        case 4:
		        	Deletevalue();
		        	break;
		        case 5:
		        	System.out.println("Existing....");
		        	break;
		        	default:
		        		System.out.println("Invalid choice.");
		        }
		       
			}while(choice != 5);
			con.close();
		}

	}


