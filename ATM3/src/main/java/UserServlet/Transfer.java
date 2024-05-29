package UserServlet;

import java.io.IOException;
import java.sql.SQLException;

import Models.Transaction;
import Models.User;
import Services.UserServices;
import Services.Utilities;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Transfer extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Utilities utils = new Utilities();
		UserServices userServices = new UserServices();
		
		if(user != null) {
			if(request.getParameter("amount") != null && request.getParameter("receiver")!= null){
				try{
					String amount = (String)request.getParameter("amount");
					String receiver = (String)request.getParameter("receiver");
					
					if(utils.getUserById(receiver) == null) {
						session.setAttribute("transferResult","<p style='color:red;'>Unable to find receiver</p>");
						response.sendRedirect(request.getContextPath() + "/Transfer.jsp");
						
					}else if(Double.parseDouble(amount) <= 0){
						session.setAttribute("transferResult","<p style='color:red;'>Unable to transfer</p>");
						response.sendRedirect(request.getContextPath() + "/Transfer.jsp");
						
					}else if(Double.parseDouble(amount) > user.getBalance()) {
						session.setAttribute("transferResult","<p style='color:red;'>Your balance is not enough to transfer</p>");
						response.sendRedirect(request.getContextPath() + "/Transfer.jsp");
						
					}else if (receiver.equals( utils.padLeftZeros(user.getIdNumber(), 8))) {
						session.setAttribute("transferResult","<p style='color:red;'>Unable to transfer</p>");
						response.sendRedirect(request.getContextPath() + "/Transfer.jsp");
						
					}else {
						Transaction transfer = userServices.transferMoney(user, utils.getUserById(receiver), Double.parseDouble(amount));
						session.setAttribute("transferResult","Money transfered to User - " + receiver + " by " + amount + "<br>" + "Transfer ID: " + transfer.getTransactionId());
						response.sendRedirect(request.getContextPath() + "/Transfer.jsp");
					}
				}catch(NumberFormatException | SQLException Error){
					session.setAttribute("transferResult","<p style='color:red;'>Wrong format</p>");
					response.sendRedirect(request.getContextPath() + "/Transfer.jsp");
				}
				
			}else {
				response.sendRedirect(request.getContextPath() + "/Error.jsp");
			}
		}
	}
}
