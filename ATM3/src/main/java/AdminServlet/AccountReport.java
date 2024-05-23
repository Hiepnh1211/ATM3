package AdminServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import Models.User;
import Services.AdminServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AccountReport extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminServices adminServices = new AdminServices();
		HttpSession session = request.getSession();
		
		try {
			List<User> userList = adminServices.accountReport();
			session.setAttribute("accounts", userList);
		} catch (SQLException e) {
			session.setAttribute("Error", "Error");
		}
	}
}
