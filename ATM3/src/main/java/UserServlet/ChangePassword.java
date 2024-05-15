package UserServlet;

import java.io.IOException;
import java.sql.SQLException;

import Models.User;
import Services.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ChangePassword extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		UserServices userServices = new UserServices();
		
		String oldPassword = (String) request.getParameter("oldPass");
		String newPassword = (String) request.getParameter("newPass");
		String confirmNewPassword = (String) request.getParameter("confirmNewPass");
		
		try {
			if(oldPassword != null){
				if(!oldPassword.equals(user.getPin())) {
					session.setAttribute("passwordChangeResult", "<p style='color:red;'>Invalid password</p>");
		  	    	response.sendRedirect(request.getContextPath() + "/ChangePassword.jsp");
					
				}else if(!newPassword.equals(confirmNewPassword)){
					session.setAttribute("passwordChangeResult", "<p style='color:red;'>Unable to confirm new password</p>");
		  	    	response.sendRedirect(request.getContextPath() + "/ChangePassword.jsp");
					
				}else if(newPassword.length() < 4){
					session.setAttribute("passwordChangeResult", "<p style='color:red;'>new password too short</p>");
		  	    	response.sendRedirect(request.getContextPath() + "/ChangePassword.jsp");
					
				}else{
					userServices.changePassword(user, oldPassword, newPassword, confirmNewPassword);
					session.setAttribute("result", "<p style='color:red;'>Your password has been change</p>");
					response.sendRedirect(request.getContextPath() + "/IdCheck.jsp");
				}
			}else{
				session.setAttribute("passwordChangeResult", "<p style='color:red;'>new password too short</p>");
	  	    	response.sendRedirect(request.getContextPath() + "/ChangePassword.jsp");
			}
		}catch (SQLException error) {
			session.setAttribute("passwordChangeResult", "<p style='color:red;'>Change password failed</p>");
  	    	response.sendRedirect(request.getContextPath() + "/ChangePassword.jsp");
		}
		
		
	}
}
