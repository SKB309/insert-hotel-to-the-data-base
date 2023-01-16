package HotelDBMS;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;

public class Hotels {

	public static Connection connection;
	public static final String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
	public static final String user = "sa";
	public static final String pass = "root";
	private static final String sqlQueryToUpdate = "";
	String userName;

	// static variables to be used from static function so it stays in static
	// context
	// " final " it can not change or update
	public static Connection con;

	static {
		try {
			con = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	static void pressEnter() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Please Press Enter key to continue...");
		sc.nextLine();

	}

	static String getDateTime(String userName) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date date = new Date(System.currentTimeMillis());
		System.out.println(userName + " Login Time : " + date);

		return dateFormat.format(date);

	}

	static String getDateTimeLogout(String userName) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date date = new Date(System.currentTimeMillis());
		System.out.println(userName + " Logout Time : " + date);

		return dateFormat.format(date);

	}

	static void mainMenue() {

		Scanner sc = new Scanner(System.in);

//		System.out.println("**********+++++++++++++ HELLO ++++++++++++************");
		System.out.println("1- Connect To DataBase ' HotelDBMS '");
		System.out.println("2- CREATE TABLE Hotel ");
		System.out.println("3- Insert Hotel Information Into Table ");
		System.out.println("4- Get By Id ");
		System.out.println("5- Update By Id ");
		System.out.println("6- Delete By Id ");
		System.out.println("7- Make Is Active False By Id ");
		System.out.println("8- Make Is Active True By Id ");
		System.out.println("0- Exit");

	}

	static void connectToDataBase() throws Throwable, IllegalAccessException, ClassNotFoundException {

		Connection connection;

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

	public static void createHotelTable() throws Throwable, IllegalAccessException, ClassNotFoundException {

		try (Connection conn = DriverManager.getConnection(url, user, pass); Statement stmt = conn.createStatement();) {
			String sql = "CREATE TABLE Hotel " + "( hotelId int PRIMARY KEY IDENTITY(1,1) ,"
					+ " hotelName VARCHAR(50) not null," + " hotelLocation VARCHAR(50)," + " created_date date ,"
					+ " updated_date date ," + " is_Active bit not null )";

			stmt.executeUpdate(sql);
			System.out.println("Created table in given database...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoTableHotels() {

		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		Scanner scanner = new Scanner(System.in);

//		System.out.println("Enter Hotel Id ");
//		Integer hotelId = scanner.nextInt();

		System.out.println("Enter Hotel Name");
		String hotelName = scanner.next();

		System.out.println("Enter Hotel location");
		String hotelLocation = scanner.next();

		System.out.println("Enter created_date");
		String created_date = scanner.next();

		System.out.println("Enter updated_date");
		String updated_date = scanner.next();

		System.out.println("Enter is_Active");
		String is_Active = scanner.next();

		String sql = "Insert into Hotel values( '" + hotelName + "','" + hotelLocation + "','" + created_date + "','"
				+ updated_date + "','" + is_Active + "')";

		// Connection class object
		Connection con;

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

	public static void getById() throws Throwable, IllegalAccessException, ClassNotFoundException {
//		take id input from the user
//		print on console
//		use SELECT query and ResultSets for showing

		Scanner sc = new Scanner(System.in);

		String serverName = "localhost";

		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
		String user = "sa";
		String pass = "root";
		Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		DriverManager.registerDriver(driver);

		connection = DriverManager.getConnection(url, user, pass);

		System.out.println(" Please Enter The ID Of The Row To Print");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();
		String sqlQueryToRead = "SELECT * FROM Hotel WHERE hotelId = " + userInput;
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToRead);

//                int count = 0; we can not use count here because it will limit my results

			while (resultSet.next()) {
				Integer id = resultSet.getInt("hotelId");
				String hotelName = resultSet.getString("hotelName");
				String hotelLocation = resultSet.getString("hotelLocation");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(id + " " + hotelName + " " + hotelLocation + " " + createdDate + " " + updatedDate
						+ " " + isActive);
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

	public static void updateById() {
//		take id input from the user
//		print on console
//		use Update query and ResultSets for showing

		System.out.println(" Please Enter The ID To Update Its Data");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();
		System.out.println(" Please Enter The New Hotel Name");
		String hotelName = inputScanner.next();
		System.out.println(" Please Enter The New Hotel Location");
		String hotelLocation = inputScanner.next();
		Date date = new Date(System.currentTimeMillis());
		Date date1 = new Date(System.currentTimeMillis());
		String sqlQueryToUpdate = "UPDATE Hotel SET hotelName = " + "'" + hotelName + "'" + ", hotelLocation = " + "'"
				+ hotelLocation + "'," + " updated_date = " + "'" + new Date(System.currentTimeMillis())
				+ "' WHERE hotelId =" + userInput;
		System.out.println(sqlQueryToUpdate);
		try {
			Statement statement = con.createStatement();
			int m = statement.executeUpdate(sqlQueryToUpdate);

		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

	public static void deleteById() {
//		take id input from the user
//		use Delete query

		System.out.println(" Please Enter The ID To Delete The Row");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();
		String sqlQueryToRead = "DELETE FROM Hotel WHERE hotelId =" + userInput;
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToRead);
			while (resultSet.next()) {
				Integer hotelId = resultSet.getInt("hotelId");
				String hotelName = resultSet.getString("hotelName");
				String hotelLocation = resultSet.getString("hotelLocation");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(hotelId + " " + hotelName + " " + hotelLocation + " " + createdDate + " "
						+ updatedDate + " " + isActive);
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

	public static void makeIsActiveFalseById() {
//		take id input from the user
//		use Update query and just update 'is_Active' column
//		Scanner sc = new Scanner(System.in);

		Scanner inputScanner = new Scanner(System.in);
		System.out.println(" Please Enter The Number Of Hotel Id  To Update Its Status");
		int userInput = inputScanner.nextInt();
		String sqlQueryToSelect = "SELECT  hotelId FROM Hotel WHERE hotelId = " + userInput;
		Statement statement;
		List<Integer> listOfIds = new ArrayList<>();
		try {
			System.out.println(sqlQueryToSelect);
			statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToSelect);
			while (resultSet.next()) {
				listOfIds.add(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Integer i : listOfIds) {
			System.out.println("hotelId is: " + i);
			String sqlQueryToUpdate = "UPDATE Hotel SET is_active = 0 where hotelId = " + i;
			System.out.println(sqlQueryToUpdate);
			try {
				statement = con.createStatement();
				int resultSet = statement.executeUpdate(sqlQueryToUpdate);
				System.out.println(resultSet);
			} catch (Exception ex) {
				System.err.println(ex);
			}
		}
	}

	static void makeIsActiveTrueById() {
		Scanner inputScanner = new Scanner(System.in);
		System.out.println(" Please Enter The Number Of Hotel Id  To Update Its Status");
		int userInput = inputScanner.nextInt();
		String sqlQueryToSelect = "SELECT  hotelId FROM Hotel WHERE hotelId = " + userInput;
		Statement statement;

		List<Integer> listOfIds = new ArrayList<>();
		try {
			System.out.println(sqlQueryToSelect);
			statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToSelect);
			while (resultSet.next()) {
				listOfIds.add(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Integer i : listOfIds) {
			System.out.println("hotelId is: " + i);
			String sqlQueryToUpdate = "UPDATE Hotel SET is_active = 1 where hotelId = " + i;
			System.out.println(sqlQueryToUpdate);
			try {
				statement = con.createStatement();
				int resultSet = statement.executeUpdate(sqlQueryToUpdate);
				System.out.println(resultSet);
//				closingConnection();
			} catch (Exception ex) {
				System.err.println(ex);
			}
		}
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
		Stack<String> historyStack = new Stack<>();

		boolean isExit = true;
		boolean isUser = true;

		pressEnter();

		while (isUser) {

			System.out.println("Entere user name");
			String userName = sc.next();
			historyStack.push(userName);

			try {
				if (!userName.equals("SAID")) {
					throw new Exception("user name is wrong");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}

			System.out.println("Entere user Password");
			String userPass = sc.next();
			historyStack.push(userPass);
			System.out.println("********** WELCOME " + userName + "************");
			System.out.println(getDateTime(userName));

			try {
				if (!userPass.equals("said")) {
					throw new Exception("user Password is wrong");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}

			do {

				mainMenue();

				int option = sc.nextInt();

				switch (option) {

				case 1:

					connectToDataBase();

					break;

				case 2:

					createHotelTable();

					break;

				case 3:

					insertIntoTableHotels();

					break;

				case 4:
					getById();

					break;

				case 5:

					updateById();

					break;

				case 6:

					deleteById();

					break;

				case 7:

					makeIsActiveFalseById();

					break;

				case 8:

					makeIsActiveTrueById();

					break;

				case 0:

					toExit();
					getDateTimeLogout(userName);
					isExit = false;

					break;

				default:

					defaults();

				}

			} while (isExit);

		}
		isUser = false;

	}
}
