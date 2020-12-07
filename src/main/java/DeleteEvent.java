package main.java;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBConnection;
import db.Events;

/**
 * Servlet implementation class DeleteEvent
 */
@WebServlet("/deleteevent")
public class DeleteEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConnection db;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEvent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		db = new DBConnection();
		db.setConnectionByProperties(getServletContext());

		String eventId = request.getParameter("eventId");
		Events event = db.getEventFromShareId(eventId);
		if (event.getRecurringID() != null) {
			db.deleteRecurringById(event.getRecurringID());
		}
		db.deleteEventById(eventId);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
