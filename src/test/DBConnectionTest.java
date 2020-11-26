package test;

import org.junit.Before;
import org.junit.Test;
import db.DBConnection;
import db.Events;

import static org.junit.Assert.assertEquals;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DBConnectionTest {
	
	private DBConnection connection;
	private Events toInsert;
	
	@Before
	public void setUp() {
		connection = new DBConnection();
		connection.setConnection("jdbc:mysql://ec2-3-137-149-170.us-east-2.compute.amazonaws.com:3306/usethisone", "dbaccess", "Z@ckery2");
		toInsert = new Events();
		toInsert.setUToken("THISISONLYATEST");
		toInsert.setTitle("Test Event");
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		toInsert.setDate(ts.toString());
		toInsert.setURL("www.google.com");
	}
	
	@Test
	public void testGetAllEventsForUser() {
		Events toCheck;
		List<Events> eList = new ArrayList<Events>(connection.getAllEventsForUser("selectAllTest"));
		toCheck = eList.get(0);
		assertEquals(toCheck.getUToken(), "selectAllTest");
		assertEquals(toCheck.getDate(), "2020-11-25 22:40:58.0");
		assertEquals(toCheck.getTitle(), "Test Event 1");
		assertEquals(toCheck.getURL(), "www.google.com");
		toCheck = eList.get(1);
		assertEquals(toCheck.getUToken(), "selectAllTest");
		assertEquals(toCheck.getDate(), "2020-11-25 22:41:02.0");
		assertEquals(toCheck.getTitle(), "Test Event 2");
		assertEquals(toCheck.getURL(), "www.google.com");
		toCheck = eList.get(2);
		assertEquals(toCheck.getUToken(), "selectAllTest");
		assertEquals(toCheck.getDate(), "2020-11-25 22:41:05.0");
		assertEquals(toCheck.getTitle(), "Test Event 3");
		assertEquals(toCheck.getURL(), "www.google.com");
		toCheck=eList.get(3);
		assertEquals(toCheck.getUToken(), "selectAllTest");
		assertEquals(toCheck.getDate(), "2020-11-25 22:43:43.0");
		assertEquals(toCheck.getTitle(), "Test Recurring Event");
		assertEquals(toCheck.getRecurringID(), "1");
		assertEquals(toCheck.getURL(), "www.google.com");
	}
	
	@Test
	public void testInsertNewEvent() throws SQLException {
		
	}
}
