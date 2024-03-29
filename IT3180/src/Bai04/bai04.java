package Bai04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class bai04 {
	public static void main(String[] args) {
		try (
				// create a database connection
				Connection connection = DriverManager.getConnection("jdbc:sqlite:bai04.db");
				Statement statement = connection.createStatement();) {
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("drop table if exists bai04");
			statement.executeUpdate("create table bai04 (username TEXT NOT NULL, password TEXT NOT NULL, PRIMARY KEY(username))");
			statement.executeUpdate("insert into bai04 values('taikhoan01', 'abcdef')");
			statement.executeUpdate("insert into bai04 values('admin', 'admin')");
			statement.executeUpdate("insert into bai04 values('taikhoan02', '123456')");
			statement.executeUpdate("insert into bai04 values('user001', 'test')");
			statement.executeUpdate("insert into bai04 values('zzzz', 'vabc')");
			statement.executeUpdate("insert into bai04 values('it3180', '1803')");
			statement.executeUpdate("insert into bai04 values('xxxx', 'yyyy')");
			// salt+hash >>> text
			String username = "taikhoan01";
			ResultSet rs = statement.executeQuery("select * from bai04 where username='" + username +"'");
			//dont inject please
			while (rs.next()) {
				// read the result set
				System.out.println("username = " + rs.getString("username"));
				System.out.println("password = " + rs.getString("password"));
			}
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			e.printStackTrace(System.err);
		}
	}
}
