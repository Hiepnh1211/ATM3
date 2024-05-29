package Filter;

import java.io.IOException;

import Models.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/DepositReport.jsp", "/WithdrawReport.jsp", "/TransferReport.jsp", "/AccountReport.jsp", "/CreateUser.jsp", "/AdminMenu.jsp"})
public class AdminLoginFilter extends HttpFilter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		User admin = (User)session.getAttribute("admin");
		
		if (admin != null ) {
			super.doFilter(request, response, chain);
		} else {
			response.sendRedirect(request.getContextPath() + "/IdCheck.jsp");
		}
	}
}
