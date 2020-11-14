package main.java;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class Home
 */
@WebServlet(urlPatterns = {"/jsonrequest"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray array = getArray();
		
		String user = request.getHeader("user");
		response.setContentType("application/javascript");
		response.getWriter().append("var data = " + array.toString());
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private JSONArray getArray() {
		
		JSONArray ret = new JSONArray();
		
		JSONObject dataSet = new JSONObject();
        dataSet.put("title", "test importjsonobject");
        dataSet.put("url", "https://www.google.com/");
        dataSet.put("start", "2020-10-02");
       
        JSONObject dataSet2 = new JSONObject();
        dataSet2.put("title", "test importjsonobject2");
        dataSet2.put("url", "https://www.google.com/");
        dataSet2.put("start", "2020-10-03");
        
        ret.put(dataSet);
        ret.put(dataSet2);
        
        return ret;
	}

}
