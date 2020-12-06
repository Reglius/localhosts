package main.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONObject;

import db.DBConnection;
import db.Events;
import db.Recurring;

/**
 * Servlet implementation class Home
 */
@WebServlet(urlPatterns = {"/jsonrequest"})
public class JSONRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int[] WEEKDAYS = new int[]{7,1,2,3,4,5,6};
	private DBConnection db;
	int count = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JSONRequest() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		db = new DBConnection();
		db.setConnectionByProperties(getServletContext());

		String user = request.getParameter("userId");		
		System.out.println("user" + user);
		
		if (user == null || user.equals("null")) {
			printDummyData(response.getWriter());
	        return;
		}
		
		JSONArray array = getArray(user);
		
		response.setContentType("application/javascript");
		
		if (count > 0) {	
			response.getWriter().append("var data = " + array.toString());
		}
		else { //this is only here for the sake of the demo
			printDummyData(response.getWriter());
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private JSONArray getArray(String user) {
		JSONArray ret = new JSONArray();
		List<Events> events = db.getAllEventsForUser(user);
		List<Events> recurringEvents = new ArrayList<>();
		List<Recurring> recEvents = db.getAllRecurringForUser(user);
		count = 0;
		
		for (Events event : events) {
			if(!(event.getRecurringID() == null) && !event.getRecurringID().equals("NULL")) {
				recurringEvents.add(event);
			}
			
			JSONObject temp = new JSONObject();
			temp.put("title", event.getTitle());
			temp.put("url", event.getURL());
			temp.put("start", event.getDate());
	        ret.put(temp);
	        
	        count++;
		}
		
		for (Events event : recurringEvents) {
			Recurring recurringFromEvent = recEvents.stream()
					.filter(o -> o.getRecurId() == Integer.parseInt(event.getRecurringID())).findFirst().get();
			
			JSONArray currentWeekly = getWeeklyEvents(event, recurringFromEvent);
			
			for (int i = 0; i < currentWeekly.length(); i++) {
				    ret.put(currentWeekly.get(i));
			}			
		}
		
        return ret;
	}
	
	private void printDummyData(PrintWriter printWriter) {
		JSONArray dummyData = new JSONArray();
		JSONObject dataSet = new JSONObject();
        dataSet.put("title", "this is a demo"); //when titles are too long then they are going over calendar boarder
        dataSet.put("url", "https://www.zoom.us/");
        dataSet.put("start", "2020-11-15 01:00:00");
        dummyData.put(dataSet);
		
        printWriter.append("var data = " + dummyData.toString());
	}
	
	public JSONArray getWeeklyEvents(Events event, Recurring recurring) {
		JSONArray ret = new JSONArray();
		
		String startArr[] = event.getDate().split(" ")[0].split("-");
		String endArr[] = recurring.getEndDate().split("-");
		
		LocalDate startDate = new LocalDate(Integer.parseInt(startArr[0]), Integer.parseInt(startArr[1]), Integer.parseInt(startArr[2]));
		LocalDate endDate = new LocalDate(Integer.parseInt(endArr[0]), Integer.parseInt(endArr[1]), Integer.parseInt(endArr[2]));

		for (int i = 0; i < 7; i++) { //checks each date to see if it is on
			startDate = new LocalDate(Integer.parseInt(startArr[0]), Integer.parseInt(startArr[1]), Integer.parseInt(startArr[2]));
			
			if (recurring.getDays().toCharArray()[i] == '1') {
				LocalDate currentDate = startDate.withDayOfWeek(WEEKDAYS[i]);
				
				if (startDate.isAfter(currentDate)) {
				    startDate = currentDate.plusWeeks(1); // start on next instance
				} else {
				    startDate = currentDate; // start on current date
				}
					
				while (startDate.isBefore(endDate)) {
					
					JSONObject temp = new JSONObject();
					temp.put("title", event.getTitle());
					temp.put("url", event.getURL());
					temp.put("start", startDate.toString() + " " + event.getDate().split(" ")[1]);
			        ret.put(temp);
				    
			        startDate = startDate.plusWeeks(1);
				}
			}
		}
		return ret;
	}


}
