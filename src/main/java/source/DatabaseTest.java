package source;

import java.sql.Connection;
import java.sql.SQLException;

import database.Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Map;
import java.util.HashMap;


/**
 * Created with IntelliJ IDEA.
 * User: Konstantin
 * Date: 24.12.13
 * Time: 1:07
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseTest {
	private Connection connection;
	private String tableName = "javabase";
	private String username =  "TestUserName";


	@Before
	public void setUp() {
		this.connection = Database.getConnect("tp_query", "root", "maxim321");
	}


	@Test
	public void testCreate() throws SQLException {
		try {
			Database.createTable(this.connection, this.tableName);
		} catch (SQLException e){
			assertEquals("could not connect", true, false);
		}
	}

	@Test
	public void testCreated() throws SQLException {
		Database.createTable(this.connection, this.tableName);
		boolean created = Database.getTable(this.connection, this.tableName);
		assertEquals(true, created);
	}

	@Test
	public void testSet() throws SQLException {
		Database.createTable(this.connection, this.tableName);

		int result = Database.set(this.connection, this.username);
		assertEquals(result, 1);
	}

	@Test
	public void testGet() throws SQLException{
		Database.createTable(this.connection, this.tableName);
		Database.set(this.connection, this.username);
		long result = Database.get(this.connection, this.username);
		assertEquals(result, 1L);
	}

	@After
	public void testDeleted() throws SQLException {
		Database.deleteTable(this.connection, this.tableName);
		assertEquals(Database.getTable(connection, this.tableName), false);
		this.connection.close();
	}
}
