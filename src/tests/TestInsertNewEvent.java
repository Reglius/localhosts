package tests;

import db.DBConnection;
import db.Events;
import static org.junit.Assert.assertEquals;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestInsertNewEvent {

	private DBConnection connection;
	private Events toInsert;
	
	@Before
	public void setUp() {
		connection = new DBConnection();
		connection.setConnection("jdbc:mysql://ec2-3-137-149-170.us-east-2.compute.amazonaws.com:3306/usethisone", "dbaccess", "Z@ckery2");
		toInsert = new Events();
		toInsert.setUToken("THISISONLYATEST");
		toInsert.setTitle("Test Event");
		toInsert.setTitle("Insert Test Event");
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		toInsert.setDate(ts.toString());
		toInsert.setURL("www.google.com");
	}
	
	@Test
	public void testInsertNewEvent() throws SQLException {

		connection.insertNewEvent(toInsert);
		Events toCheck;
		List<Events> eList = new ArrayList<Events>(connection.getAllEventsForUser("THISISONLYATEST"));
		toCheck = eList.get(0);
		assertEquals(toCheck.getUToken(), "THISISONLYATEST");
		assertEquals(toCheck.getTitle(), "Insert Test Event");
		assertEquals(toCheck.getURL(), "www.google.com");
		PreparedStatement ps = connection.getConnection().prepareStatement("DELETE FROM Events WHERE utoken = 'THISISONLYATEST'");
		ps.execute();
	}
}
