package webDriverPractice.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class R0024DatabaseTesting {

	
	private static  String url= "";
	private static  String user= "";
	private static  String password= "";
	private static String host = "localhost";
	private static String port = "9090";// could be different
	private static String databaseName;
	private static String query = "";
	private static String columnNmae;
	
	public static void main(String[] args) throws SQLException {
		url= "jdbc:mysql://"+host+":"+port+databaseName;
		// first we need to create a connection
		Connection connect = DriverManager.getConnection(url, user, password);
		
		//Statement statement = connect.createStatement() and then execute query.
		//then store the result in ResultSet object.
		ResultSet result=connect.createStatement().executeQuery(query);
		
		while (result.next()) {
			System.out.println(result.getString(columnNmae));
		}
	}
}

