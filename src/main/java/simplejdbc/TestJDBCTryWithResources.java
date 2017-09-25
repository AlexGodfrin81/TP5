package simplejdbc;

import java.sql.*;
import javax.sql.DataSource;

public class TestJDBCTryWithResources {

	public static void main(String[] args) throws Exception {
            try (   // Les ressources qui doivent être fermées automatiquement
                    Connection connection = getConnectionWithDriverManager();
                    Statement stmt = connection.createStatement(); 
                    ResultSet rs = stmt.executeQuery("SELECT * FROM CUSTOMER")
            ) {
                while (rs.next()) { 
                    int id = rs.getInt("CUSTOMER_ID");
                    String name = rs.getString("NAME");
                    String email = rs.getString("EMAIL");
                    System.out.printf("Client %d (%s), email : %s %n", id, name, email);
                }
            }
	}

	public static Connection getConnectionWithDataSource() throws SQLException {
		org.apache.derby.jdbc.ClientDataSource ds = new org.apache.derby.jdbc.ClientDataSource();
		ds.setDatabaseName("sample");
		ds.setUser("app");
		ds.setPassword("app");
		// The host on which Network Server is running
		ds.setServerName("localhost");
		// port on which Network Server is listening
		ds.setPortNumber(1527);
		return ds.getConnection();
	}

	public static Connection getConnectionWithDriverManager() throws SQLException {
		String URL = "jdbc:derby://localhost:1527/sample";
		String USERNAME = "app";
		String PASSWORD = "app";
		// On se connecte au serveur
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);	
	}
	
}
