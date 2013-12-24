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
    private Connection connection = Database.getConnect();

    @Before
    public void testCreateTable() {
        try {
            Database.createTable(connection, "testDatabaseTable");
            Map<String, Long> nameToUserId = new HashMap<String, Long>();
            nameToUserId.put("TestUserName", 1L);
            Database.set(connection, nameToUserId, "testDatabaseTable");
        } catch (SQLException e) {
            assertEquals("could not connect", true, false);
        }
    }
   @Test
    public void testGet() throws SQLException{
        long result = Database.get(connection, "TestUserName", "testDatabaseTable");
        assertEquals(result, 1L);
    }
    @After
    public void testDeleteTable() {
        try {
            Database.deleteTable(connection, "testDatabaseTable");
        } catch (SQLException e) {
            assertEquals("database was not deleted", true, false);
        }
    }
}
