package studentJdbc;
import java.sql.*;

public class TestConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//load the Jdbc driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//connect to mysqldatabase (db name: Testdb)
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Testdb","root","");
			System.out.println("✅ Connection Successful!");
			con.close();
			
		}
		catch(Exception e) {
			System.out.println("Connection Failed "+e.getMessage());
		}

	}

}
