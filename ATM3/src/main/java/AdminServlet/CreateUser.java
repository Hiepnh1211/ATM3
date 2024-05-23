package AdminServlet;

import java.io.IOException;
import java.sql.SQLException;

import Models.User;
import Services.AdminServices;
import Services.Utilities;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CreateUser extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilities utils = new Utilities();
		AdminServices adminServices = new AdminServices();
		HttpSession session = request.getSession();
		
		if(request.getParameter("name") != null && request.getParameter("contactNumber")!= null && request.getParameter("address")!= null){
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String contactNumber = request.getParameter("contactNumber");
			String address = request.getParameter("address");
			
			if(utils.checkName(name) && utils.checkContactNumber(contactNumber)){
				try {
					User newUser = adminServices.createAccount(name, contactNumber, gender, address);
					session.setAttribute("NewUser", newUser);
					response.sendRedirect(request.getContextPath() + "/CreateUser.jsp");
					
				} catch (SQLException e) {
					session.setAttribute("createResult","<p style='color:red;'>Unable to create</p>");
					response.sendRedirect(request.getContextPath() + "/CreateUser.jsp");
				}
				
			}else if(!utils.checkName(name)){
				session.setAttribute("createResult","<p style='color:red;'>Invalid name</p>");
				response.sendRedirect(request.getContextPath() + "/CreateUser.jsp");
				
			}else if(!utils.checkContactNumber(contactNumber)){
				session.setAttribute("createResult","<p style='color:red;'>Invalid contact number</p>");
				response.sendRedirect(request.getContextPath() + "/CreateUser.jsp");
			}
			
		}else if(request.getParameter("name") == null){
			session.setAttribute("createResult","<p style='color:red;'>Missing name</p>");
			response.sendRedirect(request.getContextPath() + "/CreateUser.jsp");
			
		}else if(request.getParameter("contactNumber")== null){
			session.setAttribute("createResult","<p style='color:red;'>Missing contact number</p>");
			response.sendRedirect(request.getContextPath() + "/CreateUser.jsp");
			
		}else if(request.getParameter("address")== null){
			session.setAttribute("createResult","<p style='color:red;'>Missing address</p>");
			response.sendRedirect(request.getContextPath() + "/CreateUser.jsp");
		}
	}
}
