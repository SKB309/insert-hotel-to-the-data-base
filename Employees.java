package HotelDBMS;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Employees {

	
	
	static void pressEnter() {
		Scanner sc = new Scanner(System.in);
		
	System.out.println("Please Press Enter key to continue...");
	sc.nextLine();
	
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

	static void mainMenue() {

		Scanner sc = new Scanner(System.in);

		System.out.println("**********+++++++++++++HELLO+++++++++++++************");
		System.out.println("1- Connect To DataBase ' HotelDBMS '");
		System.out.println("2- CREATE TABLE Employees ");
		System.out.println("3- Insert Employees Information Into Table ");
		System.out.println("4- Test ");
		System.out.println("0- Exit ");
	}

	public static void createEmployeesTable() throws Throwable, IllegalAccessException, ClassNotFoundException {

		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		try (Connection conn = DriverManager.getConnection(url, user, pass); Statement stmt = conn.createStatement();) {
			String sql = "CREATE TABLE Employees " + "( EmployeesId int PRIMARY KEY IDENTITY(1,1),"
					+ " employee_type_id INT FOREIGN KEY REFERENCES Employee_Type (employee_type_id) ,"
					+ " roomId  INT FOREIGN KEY REFERENCES Rooms(roomId)," + " createdDate Date not null,"
					+ " updatedDate Date," + " isActive bit not null,)";

			stmt.executeUpdate(sql);
			System.out.println("Created table in given database...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoTableRoom() {

		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter employee Type Id ");
		Integer employee_type_id = scanner.nextInt();

//		System.out.println("Enter Employees Id ");
//		Integer roomTypeId = scanner.nextInt();

		System.out.println("Enter Room Id");
		Integer roomId = scanner.nextInt();

		System.out.println("Enter created_date");
		String created_date = scanner.next();

		System.out.println("Enter updated_date");
		String updated_date = scanner.next();

		System.out.println("Enter is_Active");
		String is_Active = scanner.next();

		String sql = "Insert into Employees values(" + employee_type_id + "," + roomId + ",'" + created_date + "','"
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

	public static void getById() {
//		take id input from the user
//		print on console
//		use SELECT query and ResultSets for showing
	}

	public static void updateById() {
//		take id input from the user
//		print on console
//		use Update query and ResultSets for showing
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

				createEmployeesTable();

				break;

			case 3:

				insertIntoTableRoom();
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
