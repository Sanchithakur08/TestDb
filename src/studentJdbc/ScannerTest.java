package studentJdbc;
import java.util.Scanner;
import java.sql.*;
public class ScannerTest {
	public static void main(String[] args) throws Exception{
		Scanner sc=new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Testdb","root","");
			
		PreparedStatement ps=con.prepareStatement("INSERT into student values(?,?)");
		System.out.println("How many records do you want to insert ?");
			int n=sc.nextInt();
			for( int i=1;i<n;i++) {
				System.out.println("Enter details for student..." + i);
				//student id
				System.out.println("Enter Student Id: ");
				int id=sc.nextInt();
				sc.nextLine();
				//student name
				System.out.println("Enter Student name: ");
				String name=sc.next();
				sc.nextLine();
				
				ps.setInt(1, 100);
				ps.setString(2,"Kirat");
				int row=ps.executeUpdate();
					
				ps.setInt(1,101);
				ps.setString(2,"Sanchi");
				int row1=ps.executeUpdate();

				System.out.println(row + " Record inserted. ");
				System.out.println(row1+ " Record inserted. ");
				con.close();
				sc.close();
		}
	
	}

}
