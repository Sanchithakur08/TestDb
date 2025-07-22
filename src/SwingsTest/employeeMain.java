package SwingsTest;
import java.util.List;
import java.util.Scanner;
public class employeeMain {

	public static void main(String[] args)throws Exception {
		Scanner sc=new Scanner(System.in);
		employeeCRUD crud=new employeeCRUD();
		while(true) {
			System.out.println("\n------Employee Management-----");
			System.out.println("1.Add Employee");
			System.out.println("2.View Employee ");
			System.out.println("3.Update Employee");
			System.out.println("4.Delete Employee");
			System.out.println("5.Exit");
			System.out.println("Enter your choice: ");
			int choice=sc.nextInt();
			sc.nextLine();
			;
			switch(choice) 
			{
			case 1:
				employeeDatabase emp=new employeeDatabase();
				System.out.println("Enter Id: ");
				int id=sc.nextInt();
				emp.setEmp_id(id);
				sc.nextLine();
				
				System.out.println("Enter Name: ");
				String name=sc.next();
				emp.setEmp_name(name);
				sc.nextLine();
				
				System.out.println("Enter Salary: ");
				int salary=sc.nextInt();
				emp.setEmp_salary(salary);
				sc.nextLine();
				
				System.out.println("Enter department: ");
				String dept=sc.next();
				emp.setEmp_dept(dept);
				sc.nextLine();
				crud.createEmployee(emp);
				break;
			case 2:
				List<employeeDatabase> emp1=crud.getAllEmployees();
				System.out.println("Employees records are:");
				for(employeeDatabase emp2:emp1)
				{
					System.out.println("Id:"+emp2.getEmp_id()+ "name:"+ emp2.getEmp_name()+"salary:"+emp2.getEmp_salary()+"DEpartment:"+emp2.getEmp_dept());
				}
				break;
			case 3:
				employeeDatabase updateEmp=new employeeDatabase();
				System.out.println("Enter ID: ");
				int updateID=sc.nextInt();
				updateEmp.setEmp_id(updateID);
				
				System.out.println("Enter New Name: ");
				String name1=sc.next();
				updateEmp.setEmp_name(name1);
				sc.nextLine();
				
				System.out.println("Enter Salary: ");
				int salary1=sc.nextInt();
				updateEmp.setEmp_salary(salary1);
				sc.nextLine();
				
				System.out.println("Enter department: ");
				String dept1=sc.next();
				updateEmp.setEmp_dept(dept1);
				sc.nextLine();
		
				crud.updateEmployee(updateEmp);
				break;
			case 4:
				System.out.println("Enter ID to delete: ");
				int deleteid=sc.nextInt();
				crud.deleteEmployee(deleteid);
				break;
			case 5:
				System.out.println("Exiting...........");
				sc.close();
				System.exit(0);
			default:
				System.out.println("Invalid choice ! TRy again.");
				
				
			}
		}
	

	}

}
