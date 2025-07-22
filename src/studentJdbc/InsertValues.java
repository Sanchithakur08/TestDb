package studentJdbc;
import java.sql.*;
public class InsertValues {
	public static void main(String[] args) throws Exception {
	
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Testdb","root","");
		
		PreparedStatement ps=con.prepareStatement("INSERT into student values(?,?)");
		ps.setInt(1,100);
		ps.setString(2, "Kirat");
		int row=ps.executeUpdate();
		
		
		ps.setInt(1,101);
		ps.setString(2,"Sanchi");
		int row1=ps.executeUpdate();
	
		System.out.println(row + " Record inserted. ");
		System.out.println(row1+ " Record inserted. ");
		con.close();

	}
}
