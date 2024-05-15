import java.sql.SQLException;

import Services.UserServices;
import Services.Utilities;

public class test {
	public static void main(String[] args) {
		Utilities utils = new Utilities();
		UserServices userServices = new UserServices();
		
		try {
			System.out.println(userServices.transferMoney(utils.getUserById("17441786"), utils.getUserById("26885995"), 1).getTransactionId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
