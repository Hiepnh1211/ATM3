import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import Services.AdminServices;
import Services.Constants;
import Services.UserServices;
import Services.Utilities;

public class test {
	public static void main(String[] args) {
		Utilities utils = new Utilities();
		UserServices userServices = new UserServices();
		AdminServices adminServices = new AdminServices();
		
		try {
			System.out.println(userServices.checkTransactionLimit("05464045", Constants.FUNCTION_DEPOSIT));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
