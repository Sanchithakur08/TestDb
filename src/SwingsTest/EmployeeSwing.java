package SwingsTest;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.*;
public class EmployeeSwing {
	static Connection con;
	public static void connect()throws Exception{
	Class.forName("com.mysql.cj.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Testdb","root","");
}
	public static void main(String[] args) throws Exception{
		connect();
		JFrame frame=new JFrame("Background Image");
		frame.setSize(600,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	//	frame.setLayout(null);
	//	frame.setVisible(true);
		//JFrame frame=new JFrame ("Employee Management");
     	frame.setLocationRelativeTo(null);
	    frame.setVisible(true);

		 //labels
		 ImageIcon bgImage=new ImageIcon("C:\\Users\\91981\\Desktop\\office2.jpg");
		//ImageIcon bgImage=new ImageIcon("C:\\Users\\91981\\Desktop\\off2.jpg");
		// ImageIcon bgImage=new ImageIcon("office2.jpg");
		 JLabel background =new JLabel(bgImage);
		// JPanel backgroundPanel=new JPanel();
		 
			// protected void paintComponent(Graphics g) {
		//		 super.paintComponent(g);
			//	 g.drawImage(bgImg, getImage(), 0, 0, getWidth(), getHeight(),this);
			// }
		 
		 background.setLayout(new BorderLayout());
         JLabel label=new JLabel("Hello Background!")		 ;
         //label.setForeground(Color.white);
         label.setHorizontalAlignment(SwingConstants.CENTER);
         frame.setContentPane(background);
         //frame.setVisible(true);
         
		 //Labels
		 Font f=new Font("Seoge UI",Font.BOLD,16);
		 JLabel l1=new JLabel(" Employee ID: ", SwingConstants.CENTER);
		 l1.setBounds(30, 40, 140,25);
		 l1.setFont(f);	
		 l1.setOpaque(true);
		 l1.setForeground(Color.black);
		 //l1.setForeground(new Color(0,0,153));
		 l1.setBackground(new Color(220,220,220));
		 
		 
		 JLabel l2=new JLabel("Employee Name: ",SwingConstants.CENTER);
		 l2.setBounds(30, 80, 140, 25); 
		 l2.setFont(f);
		 l2.setForeground(Color.black);
		// l2.setForeground(new Color(0,0,153));
		 l2.setBackground(new Color(220,220,220));
		 l2.setOpaque(true);
		 
		 JLabel l3=new JLabel("Employee Salary: ",SwingConstants.CENTER);
		 l3.setBounds(30, 120, 140, 25);
		 l3.setFont(f);
		 l3.setForeground(Color.black);
		//l3.setForeground(new Color(0,0,153));
		l3.setBackground(new Color(220,220,220));
		l3.setOpaque(true);
		 
		 JLabel l4=new JLabel("Employee Dept: ",SwingConstants.CENTER);
		 l4.setBounds(30, 160, 140, 25);
		 l4.setFont(f);
		 l4.setForeground(Color.black);
		// l4.setForeground(new Color(0,0,153));
		 l4.setBackground(new Color(220,220,220));
		 l4.setOpaque(true);

		
		 
		 //bkg.setLayout(null);
		 //textfields
		 JTextField t1 = new JTextField(15);
		 t1.setBounds(180,40,170,25);;
	
		 JTextField t2 = new JTextField();
		 t2.setBounds(180,80,170,25);
		 
		 JTextField t3 = new JTextField();
		 t3.setBounds(180,120,170,25);
		 
		 JTextField t4 = new JTextField();
		 t4.setBounds(180,160,170,25);
		 //buttons
		 Font bf=new Font("Verdana", Font.BOLD,14);
		 JButton addBtn=new JButton ("ADD");
		 addBtn.setBounds(40,220,100,30);
		 addBtn.setFont(bf);
		 
		 JButton viewBtn=new JButton ("VIEW");
		 viewBtn.setBounds(150,220,100,30);
		 viewBtn.setFont(bf);
		 
		 JButton deleteBtn=new JButton ("DELETE");
		 deleteBtn.setBounds(260,220,100,30);
		 deleteBtn.setFont(bf);
		 
		 JButton updateBtn=new  JButton("UPDATE");
		 updateBtn.setBounds(380,220,100,30);
		 updateBtn.setFont(bf);
		 
			
		 
		 //add button logic
		 addBtn.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 String name = t2.getText().trim();
				 String salary = t3.getText().trim();
				 String department = t4.getText().trim();
				 
				 if(name.isEmpty() || (department.isEmpty())) {
					 JOptionPane.showMessageDialog(null, "Name and Department cannot be empty.");
			            return;
				 }
			    try {
			            if (Integer.parseInt(salary)<=20000) {
			                JOptionPane.showMessageDialog(null, "Salary must be at least ₹20,000.");
			                return;
			            }
			    } catch (NumberFormatException ex) {
			            JOptionPane.showMessageDialog(null, "Please enter a valid number for salary.");
			            return;
			        }
				 try {
					 String sql="INSERT INTO employee values(?,?,?,?)";
		             PreparedStatement ps=con.prepareStatement(sql);
		             ps.setInt(1, Integer.parseInt(t1.getText()));
		             ps.setString(2, t2.getText());
		             ps.setInt(3, Integer.parseInt(t3.getText()));
		             ps.setString(4, t4.getText());
		             ps.executeUpdate();
		             JOptionPane.showMessageDialog(frame, "Employee Added");
		             t1.setText("");
		             t2.setText("");
		             t3.setText("");
		             t4.setText("");   
				 }
				 catch(Exception ex) {
					 JOptionPane.showMessageDialog(frame,"insert Failed: " +ex.getMessage()); 
				 } 
				 } 
		 });
	
    viewBtn.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		try {
			String all="";
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SElect * from employee");
			while(rs.next()) {
				all+=rs.getInt(1) 
						+ ","
						+rs.getString(2) +","
						+rs.getInt(3) + ","
						+rs.getString(4)+ 
						"\n";
				}
			JTextArea area=new JTextArea(all);
			JScrollPane scroll=new JScrollPane(area);
			scroll.setPreferredSize(new java.awt.Dimension(400,200));
			JOptionPane.showMessageDialog(frame,scroll,"Employee Records",JOptionPane.INFORMATION_MESSAGE); 
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(frame,"View Failed: " +ex.getMessage()); 
		}
	}	
	
});
//delete all
      deleteBtn.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
    		  String empId=t1.getText().trim();
    		  if(empId.isEmpty()) {
    			  JOptionPane.showMessageDialog(frame,"Please enter Employee ID to delete."); 
    			  return;  
    		  }
    		  try {
    			  PreparedStatement ps=con.prepareStatement("DELETE from employee where emp_id=?");
    			  ps.setInt(1, Integer.parseInt(empId));
    			  int rows=ps.executeUpdate();
    			  if(rows>0) {
    				  JOptionPane.showMessageDialog(frame,"Record with ID " + empId +" deleted successfully.");
    				  t1.setText("");
    				  t2.setText("");
    				  t3.setText("");
    				  t4.setText("");
    			  }else {
    				  JOptionPane.showMessageDialog(frame,"No record found with ID: " + empId); 
    			  }
    			  //Statement stmt=con.createStatement();
    			 // stmt.executeUpdate("DELETE from employee");
    			 // JOptionPane.showMessageDialog(frame,"All records deletd..!"); 
    		  }
    		  catch(Exception ex) {
    			  JOptionPane.showMessageDialog(frame,"Delete Failed: " +ex.getMessage()); 
    		  }
    	  }

 });
      updateBtn.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
    		  try {
    			  String sql="UPDATE employee SET emp_name=?,emp_salary=?,emp_dept=? where emp_id=?";
		             PreparedStatement ps=con.prepareStatement(sql);
		      
		             ps.setString(1, t2.getText());                         // emp_name
		             ps.setInt(2, Integer.parseInt(t3.getText()));         // emp_salary
		             ps.setString(3, t4.getText());                         // emp_dept
		             ps.setInt(4, Integer.parseInt(t1.getText()));         //emp_id
		             ps.executeUpdate();

		             
		             JOptionPane.showMessageDialog(frame, "Employee Updated");
		             t1.setText("");
		             t2.setText("");
		             t3.setText("");
		             t4.setText("");   
    		  }catch(Exception ex) {
    			  JOptionPane.showMessageDialog(frame,"Update Failed: " +ex.getMessage()); 
    		  }
    	  }
      });
      
      
      //frame.setSize(800,600);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 //load image
		// ImageIcon bgicon=new ImageIcon("office2.jpg");
	//	 Image img = bgicon.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
		// JLabel bkg= new JLabel(new ImageIcon(img));
		// bkg.setBounds(10,10,800,600);
		// bkg.setLayout(null);
		 
	//	 frame.setContentPane(bkg);
		// frame.setLayout(null);
		// frame.setVisible(true);
		 
		  background.add(l1); 
		  background.add(l2);
		  background.add(l3); 
		  background.add(l4);
		  background.add(t1);
		  background.add(t2); background.add(t3); background.add(t4); background.add(addBtn);
		 background.add(viewBtn); background.add(deleteBtn); background.add(updateBtn);
		 
     frame.setLayout(null);
     frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
