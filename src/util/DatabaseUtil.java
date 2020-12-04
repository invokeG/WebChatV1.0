package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class DatabaseUtil {
		public Connection getConnection() {
			Connection conn = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				String url = "jdbc:mysql://127.0.0.1:3306/web?useSSL=false&&serverTimezone=GMT";
				conn = DriverManager.getConnection(url, "root", "123123");
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conn;
		}

		public void freeConnection(Connection conn) {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
}
