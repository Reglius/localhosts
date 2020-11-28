package main.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import db.DBConnection;
import db.Events;

/**
 * Servlet implementation class Home
 */
@WebServlet(urlPatterns = {"/jsonrequest"})
public class JSONRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
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
		db.getConnectionByProperties(getServletContext());
		
		String user = request.getParameter("userId");
		
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
		count = 0;
		
		for (Events event : db.getAllEventsForUser(user)) {
			count++;
			JSONObject temp = new JSONObject();
			temp.put("title", event.getTitle());
			temp.put("url", event.getURL());
			temp.put("start", event.getDate());
	        ret.put(temp);
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

}














