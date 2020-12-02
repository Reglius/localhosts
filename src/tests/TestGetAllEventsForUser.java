package tests;
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

public class TestGetAllEventsForUser {

	private DBConnection connection;
	private Events toInsert;

	@Before
	public void setUp() {
		connection = new DBConnection();
		connection.setConnection("jdbc:mysql://ec2-3-137-149-170.us-east-2.compute.amazonaws.com:3306/usethisone", "dbaccess", "Z@ckery2");
	}

	@Test
	public void testGetAllEventsForUser() {
		Events toCheck;
		List<Events> eList = new ArrayList<Events>(connection.getAllEventsForUser("selectAllTest"));
		toCheck = eList.get(0);
		assertEquals("selectAllTest", toCheck.getUToken());
		assertEquals("2020-11-25 22:40:58.0", toCheck.getDate());
		assertEquals("Test Event 1", toCheck.getTitle());
		assertEquals("www.google.com", toCheck.getURL());
		toCheck = eList.get(1);
		assertEquals("selectAllTest", toCheck.getUToken());
		assertEquals("2020-11-25 22:41:02.0", toCheck.getDate());
		assertEquals("Test Event 2", toCheck.getTitle());
		assertEquals("www.google.com", toCheck.getURL());
		toCheck = eList.get(2);
		assertEquals("selectAllTest", toCheck.getUToken());
		assertEquals("2020-11-25 22:41:05.0", toCheck.getDate());
		assertEquals("Test Event 3", toCheck.getTitle());
		assertEquals("www.google.com", toCheck.getURL());
		toCheck=eList.get(3);
		assertEquals("selectAllTest", toCheck.getUToken());
		assertEquals("2020-11-28 20:28:09.0", toCheck.getDate());
		assertEquals("Test Recurring Event", toCheck.getTitle());
		assertEquals("1", toCheck.getRecurringID());
		assertEquals("www.google.com", toCheck.getURL());
	}
}