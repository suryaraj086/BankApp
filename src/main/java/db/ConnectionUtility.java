package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import myexception.CustomException;

public enum ConnectionUtility {
CONNECTION;
	static final String url="jdbc:mysql://localhost:3306/bank";
	static final String name="root";
	static final String password="1234";
	static Connection connect=null;


public static Connection getConnection() throws CustomException {
	try {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	try {
	if(connect==null)
	{
		connect=DriverManager.getConnection(url,name,password);
		return connect;
	}
	return connect;
	}
	catch (Exception e) {
	throw new CustomException("connection error"); 
	}
}

public static void closeConnection() throws SQLException {
	if(connect!=null)
	{
		connect.close();
	}
}
	
}

