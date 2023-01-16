package HotelDBMS;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Employee_Type {


	static void pressEnter() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Please Press Enter key to continue...");
		sc.nextLine();

	}

	static void mainMenue() {

		Scanner sc = new Scanner(System.in);

		System.out.println("**********+++++++++++++HELLO+++++++++++++************");
		System.out.println("1- Connect To DataBase ' HotelDBMS '");
		System.out.println("2- Create Table For Employee Type ");
		System.out.println("3- Insert Employee Type Information Into Table ");
		System.out.println("4- Test ");
		System.out.println("0- Exit ");
	}

	static void connectToDataBase() throws Throwable, IllegalAccessException, ClassNotFoundException {

		Connection connection = null;

		try {

			// Create a connection to the database

			String serverName = "localhost";

			String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
			String user = "sa";
			String pass = "root";
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);

			connection = DriverManager.getConnection(url, user, pass);

			System.out.println("Successfully Connected to the database!" + " HotelDBMS ");

		} catch (SQLException e) {

			System.out.println("Could not connect to the database " + e.getMessage());
		}

	}

	public static void createEmployee_TypeTable() throws Throwable, IllegalAccessException, ClassNotFoundException {

		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		try (Connection conn = DriverManager.getConnection(url, user, pass); Statement stmt = conn.createStatement();) {
			String sql = "CREATE TABLE Employee_Type " + "( employee_type_id int  PRIMARY KEY IDENTITY(1,1),"
					+ " employee_type_name VARCHAR(50) not null," + " createdDate Date not null," + " updatedDate Date,"
					+ " isActive bit ,)";

			stmt.executeUpdate(sql);
			System.out.println("Created table for Employee_Type in given database...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoTableEmployee_Type() {

		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		Scanner scanner = new Scanner(System.in);

//		System.out.println("Enter Employee Type Id ");
//		Integer EmployeeTypeId  = scanner.nextInt();

		System.out.println("Enter Employee Type Name");
		String EmployeeTypeName = scanner.next();

//		System.out.println("Enter Room location");
//		String hotelLocation = scanner.next();

		System.out.println("Enter created_date");
		String created_date = scanner.next();

		System.out.println("Enter updated_date");
		String updated_date = scanner.next();

		System.out.println("Enter is_Active");
		String is_Active = scanner.next();

		String sql = "Insert into Employee_Type values( '" + EmployeeTypeName + "','" + created_date + "','"
				+ updated_date + "','" + is_Active + "')";
		// Connection class object
		Connection con = null;

		// Try block to check for exceptions
		try {

			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			// Registering drivers
			DriverManager.registerDriver(driver);

			// Reference to connection interface
			con = DriverManager.getConnection(url, user, pass);

			// Creating a statement
			Statement st = con.createStatement();

			// Executing query
			int m = st.executeUpdate(sql);
			if (m >= 1)
				System.out.println("inserted successfully : " + sql);
			else
				System.out.println("insertion failed");

			// Closing the connections
			con.close();

		}

		// Catch block to handle exceptions
		catch (Exception ex) {
			// Display message when exceptions occurs
			System.err.println(ex);
		}
	}

	public static ArrayList<String> getById() throws Throwable {
		
//		take id input from the user
//		print on console
//		use SELECT query and ResultSets for showing
		Scanner sc = new Scanner(System.in);
		
		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
		String user = "sa";
		String pass = "root";
		
//		public ArrayList<String> ShowResults(){

            Connection connection = null;
//            String url = "jdbc:mysql://localhost:3306/";
//            String dbName = "******";
            String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//            String userName = "******";
//            String password = "******";
            ArrayList<String> rowArray = new ArrayList<String>();

            try{
                  Class.forName(driverName).newInstance();
                  connection = DriverManager.getConnection(url, user, pass);

                  try{
                    Statement stmt = connection.createStatement();
                    String selectquery = "SELECT*FROM Employee_Type  ";
                    ResultSet rs = stmt.executeQuery(selectquery);

//                    WHERE employee_type_id
                    
                    
                    while(rs.next()){
                      rowArray.add(rs.getString(1));
                      rowArray.add(rs.getString(2));
                      rowArray.add(rs.getString(3));

                      System.out.println(rowArray);
                    }
                  }
                  catch(SQLException s){
                    System.out.println(s);
                  }
                  connection.close();
                }
                catch (Exception e){
                  e.printStackTrace();
                }

            return rowArray;
        }


		
		
	

	public static void updateById() throws Throwable {
//		take id input from the user
//		print on console
//		use Update query and ResultSets for showing
		

		ResultSet employee_type_id = null;
		ResultSet rs = employee_type_id ;
		while (rs.next()) {
			String category = rs.getString("Category");
			System.out.println(category);
			}
		
	}

	public static void deleteById() {
//		take id input from the user
//		use Delete query
	}

	public static void makeIsActiveFalseById() {
//		take id input from the user
//		use Update query and just update 'is_Active' column
	}

	public static void insertIntoTable() {

	}

	static void toExit() {

		System.out.println("* * * * * * *  Exit  * * * * * * * * * *");
		System.out.println("* * * * * *  THANK YOU  * * * * * * * *");
		System.out.println("**********SEE YOU SOON**********");

	}

	static void defaults() {

		System.out.println("Please Enter courrect choise");

	}

	static String getDateTime() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static void main(String[] args) throws Throwable, ClassNotFoundException, Throwable {

		Scanner sc = new Scanner(System.in);

		boolean isExit = true;

		pressEnter();

		do {

			mainMenue();

			int option = sc.nextInt();

			switch (option) {

			case 1:

				connectToDataBase();

				break;

			case 2:

				createEmployee_TypeTable();

				break;

			case 3:

				insertIntoTableEmployee_Type();
				break;

			case 4:
				getById();

				break;

			case 0:

				toExit();
				isExit = false;

				break;

			default:

				defaults();
			}

		} while (isExit);

	}

	


	

}
