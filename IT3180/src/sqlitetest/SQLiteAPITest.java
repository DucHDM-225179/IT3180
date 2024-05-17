package sqlitetest;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteAPITest {

	private static Connection connection;
	private static Statement statement;
	
	private static final String RANCHAR_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String RANCHAR_LOWER = RANCHAR_UPPER.toLowerCase();
	private static final String RANCHAR_DIGIT = "0123456789";
	private static final String RANCHAR = RANCHAR_UPPER + RANCHAR_LOWER + RANCHAR_DIGIT;
	private static final SecureRandom rnd = new SecureRandom();
	
	private static final int SQLITE_CONSTRAINT_PRIMARYKEY = 19;
	
	public static String genRandomString(int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; ++i) {
			sb.append(RANCHAR.charAt(rnd.nextInt(RANCHAR.length())));
		}
		return sb.toString();
	}
	
	private static boolean addUser(String username, String password) {
		try {
			statement.executeUpdate("INSERT INTO test01 "
					+ "(username, password)"
					+ "VALUES"
					+ "(" + "\"" + username + "\"" + ","
					+ "\"" + password + "\"" + ")");
		}
		catch (SQLException e) {
			if (e.getErrorCode() == SQLITE_CONSTRAINT_PRIMARYKEY) {
				return false;
			}
			else {
				System.out.println (e.getErrorCode());
				e.printStackTrace(System.err);
				return false;
			}
		}
		
		return true;
	}
	private static void getRandomUser() {
		try {
			ResultSet rs = statement.executeQuery("SELECT * FROM test01 ORDER BY RANDOM() LIMIT 1");
			while (rs.next()) {
				System.out.println(rs.getString("username"));
				System.out.println(rs.getString("password"));
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace(System.err);
		}
	}
	
	private static boolean addRandomUser() {
		String username = genRandomString(3);
		String password = genRandomString(32);
		
		return addUser(username, password);
	}
	
	public static void main(String args[]) {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:sqliteAPITest.db");
			statement = connection.createStatement();
			
			statement.executeUpdate("DROP TABLE IF EXISTS test01");
			statement.executeUpdate("CREATE TABLE test01 "
					+ "(username TEXT NOT NULL, "
					+ "password TEXT NOT NULL, "
					+ "PRIMARY KEY(username))");
			
			for (int i = 0; i < 10000; ++i) {
				addRandomUser();
				if (i % 100 == 0) {
					long start = System.nanoTime();
					System.out.print('=');
					getRandomUser();
					long end = System.nanoTime();
					
					System.out.println("Time taken: " + (end-start)/1000);
				}
			}
			System.out.println("Done");
			
			statement.close();
			connection.close();
		}
		catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			e.printStackTrace(System.err);
		}
	}
}
