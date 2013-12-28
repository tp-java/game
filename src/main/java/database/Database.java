package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import base.AccountService;
import junit.framework.TestCase;
import java.util.Map;
import java.util.HashMap;
import java.sql.PreparedStatement;

/**
 * Created with IntelliJ IDEA.
 * User: Konstantin
 * Date: 21.12.13
 * Time: 10:35
 * To change this template use File | Settings | File Templates.
 */
public class Database {
	public static Connection getConnect(String dbName, String username, String password) { //TODO в экземпляре класса поле с коннектом держать
		//TODO передавать в конструкторе
		String dbUrl = "jdbc:mysql://localhost/" + dbName;
		String dbClass = "com.mysql.jdbc.Driver";
		try {
			Class.forName(dbClass);
			return DriverManager.getConnection(dbUrl,
					username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static int set(Connection connection, String username) {
		String tableName = "javabase";
		try{
			String update = "insert into " + tableName + " (`name`) values (?)";
			PreparedStatement stmt = connection.prepareStatement(update);
			int updated = 0;
			try {
				stmt.setString(1, username);
				stmt.executeUpdate();
				updated++;
			} catch (SQLException e) {
				e.printStackTrace();
			}

			stmt.close();
			return updated;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static long get(Connection connection, String param) {
		String tableName = "javabase";
		String query = "SELECT * FROM " + tableName + " WHERE name = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, param);
			ResultSet result = statement.executeQuery();
			Long userId = -1L;
			while (result.next()) {
				userId = result.getLong(1);
			}
			statement.close();

			return userId;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static boolean getTable(Connection connection, String tableName) {
		String query = "SHOW COLUMNS FROM `" + tableName + "`";
		boolean created = false;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				created = true;
			}
			return created;
		} catch (SQLException e) {
			return false;
		}
	}

	public static void createTable(Connection connection, String tableName) throws SQLException{
		String query = "CREATE TABLE IF NOT EXISTS`" + tableName + "` (userid int AUTO_INCREMENT PRIMARY KEY, name varchar(45))";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.executeUpdate();
		stmt.close();
	}

	public static void deleteTable(Connection connection, String tableName) throws SQLException{
		String query = "DROP TABLE IF EXISTS " + tableName;
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.executeUpdate();
		stmt.close();
	}
}

