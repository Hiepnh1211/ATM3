package UserServlet;

import java.io.IOException;

import Models.User;
import Services.Constants;
import Services.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PasswordCheck extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserServices userServices = new UserServices();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("login");
		
		if(password.length() == 4){
			if(userServices.checkPassword(user.getIdNumber(), password)){
	  			
	  			if(user.getRole().equals(Constants.USER_ROLE)){
	  				session.setAttribute("user", user);
	  				response.sendRedirect(request.getContextPath() + "/UserMenu.jsp");
	  				
	  			}else if(user.getRole().equals(Constants.ADMIN_ROLE)){
	  				session.setAttribute("admin", user);
	  				response.sendRedirect(request.getContextPath() + "/AdminMenu.jsp");
	  			}
	  		}else{
	  			session.setAttribute("passwordResult", "<p style='color:red;'>Invalid Password</p>");
	  	    	response.sendRedirect(request.getContextPath() + "/Password.jsp");
	  		}
			
		}else{
			session.setAttribute("passwordResult", "<p style='color:red;'>Wrong Password</p>");
	    	response.sendRedirect(request.getContextPath() + "/Password.jsp");
		}
	}
}
