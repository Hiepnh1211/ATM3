package AdminServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import Models.User;
import Services.AdminServices;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AccountReport")
public class AccountReport extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AdminServices adminServices = new AdminServices();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
//		try {
//			int page = 1;
//			int recordsPerPage = 5;
//			if (request.getParameter("page") != null) {
//	            page = Integer.parseInt(request.getParameter("page"));
//			}
//			List<User> userList = adminServices.accountReport((page-1)*recordsPerPage, recordsPerPage);
//			int numberOfRecords = adminServices.getnumberOfRecord();
//			System.out.println(numberOfRecords);
//			int numberOfPages = (int)Math.ceil(numberOfRecords * 1.0 /recordsPerPage); 
//			System.out.println(numberOfPages);
//			
//			request.setAttribute("userList", userList);
//			request.setAttribute("numberOfPages", numberOfPages);
//			request.setAttribute("currentPage", page);
//			RequestDispatcher view  = request.getRequestDispatcher("AccountReport.jsp");
//			view.forward(request, response);
//			
////			session.setAttribute("accounts", userList);
//		} catch (SQLException e) {
//			session.setAttribute("Error", "Error");
//		}
	}
}
