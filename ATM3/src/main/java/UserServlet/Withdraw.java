package UserServlet;

import java.io.IOException;
import java.sql.SQLException;

import Models.Transaction;
import Models.User;
import Services.Constants;
import Services.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Withdraw extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		double balance = user.getBalance();
		UserServices userServices = new UserServices();
		
		if(request.getParameter("amount") != null){
				try{
					String amount = (String)request.getParameter("amount");
					if(Double.parseDouble(amount) > Constants.MAXIMUM_TRANSACTION_AMOUNT){
	  					session.setAttribute("withdrawResult","<p style='color:red;'>You are depositting to much</p>");
	  					response.sendRedirect(request.getContextPath() + "/Withdraw.jsp");
	  					
	  				}else if( Double.parseDouble(amount) <= 0){
	  					session.setAttribute("withdrawResult","<p style='color:red;'>Please Deposit</p>");
	  					response.sendRedirect(request.getContextPath() + "/Withdraw.jsp");
	  					
	  				}else if(!userServices.checkTransactionLimit(user.getIdNumber(), Constants.FUNCTION_WITHDRAW)) {
	  					session.setAttribute("withdrawResult","<p style='color:red;'>You have ran out of depositting times today</p>");
	  					response.sendRedirect(request.getContextPath() + "/Withdraw.jsp");
	  					
	  				}else if(Double.parseDouble(amount) > user.getBalance()) {
	  					session.setAttribute("result","<p style='color:red;'>Unable to deposit</p>");
	  					response.sendRedirect(request.getContextPath() + "/Withdraw.jsp");
	  				}else {
	  					
	  					Transaction transaction = userServices.withdraw(user, Double.parseDouble(amount));
						
		  				if(Double.parseDouble(amount) > 0 && transaction !=null) {
		  					session.setAttribute("withdrawResult","Deposited, you balance: " + balance + " -> " + user.getBalance() +"<br>\n" + "Transaction ID: " + transaction.getTransactionId());
		  					response.sendRedirect(request.getContextPath() + "/Withdraw.jsp");
		  				}
	  				}
					
				}catch(NumberFormatException | SQLException Error){
					session.setAttribute("withdrawResult","<p style='color:red;'>Wrong format</p>");
					response.sendRedirect(request.getContextPath() + "/Withdraw.jsp");
					Error.printStackTrace();
				}
		}	

	}
}
