package Filter;

import java.io.IOException;

import Models.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/Deposit.jsp", "/Withdraw.jsp", "/Transfrer", "/ChangePassword.jsp", "/BalanceEnquiry.jsp", "/UserMenu.jsp"})
public class UserLoginFilter extends HttpFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 1 check user exists in session
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if (user != null ) {
			super.doFilter(request, response, chain);
		} else {
			response.sendRedirect(request.getContextPath() + "/IdCheck.jsp");
		}
		
	}
	
}
