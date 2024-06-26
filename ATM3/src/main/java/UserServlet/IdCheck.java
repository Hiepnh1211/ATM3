package UserServlet;


import java.io.IOException;

import Services.Utilities;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class IdCheck extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Utilities utils = new Utilities();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		HttpSession session = request.getSession();
    	
    	if(username.length() < 8){
    		session.setAttribute("result", "<p style='color:red;'>Invalid User ID</p>");
    		response.sendRedirect(request.getContextPath() + "/IdCheck.jsp");
    		
    	}else if (utils.getUserById(username) == null) {
    		session.setAttribute("result", "<p style='color:red;'>Wrong User ID</p>");
    		response.sendRedirect(request.getContextPath() + "/IdCheck.jsp");
    	}else {
    		
    		session.setAttribute("login", utils.getUserById(username));
        	response.sendRedirect(request.getContextPath() + "/Password.jsp");
    	}
	}
}
