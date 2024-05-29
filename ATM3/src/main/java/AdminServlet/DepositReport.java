package AdminServlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import Models.Transaction;
import Models.User;
import Services.AdminServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DepositReport extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminServices adminServices = new AdminServices();
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("admin");
			if(request.getParameter("date") != null){
				String date = request.getParameter("date"); 
				session.setAttribute("date", date);
				
				String[] pickedDate = date.split("/");
				Date checkDate = Date.valueOf(LocalDate.of(Integer.valueOf(pickedDate[2]), Integer.valueOf(pickedDate[0]), Integer.valueOf(pickedDate[1])));
				try {
					List<Transaction>depositRecord = adminServices.depositReport(checkDate);
					
					if(depositRecord != null) {
						session.setAttribute("Deposit", depositRecord);
						response.sendRedirect(request.getContextPath() + "/DepositReport.jsp");
					}
					
				} catch (SQLException Error) {
					session.setAttribute("Empty", "Unable to find any tranasctions");
		        	
				}
			}
	}
}
