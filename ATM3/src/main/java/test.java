import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import Models.User;
import Services.AdminServices;
import Services.Constants;
import Services.UserServices;
import Services.Utilities;

public class test {
	public static void main(String[] args) {
		Utilities utils = new Utilities();
		UserServices userServices = new UserServices();
		AdminServices adminServices = new AdminServices();
		
//		int page = 1;
//		int recordsPerPage = 5;
//		try {
//			List<User> userList = adminServices.accountReport((page-1)*recordsPerPage, recordsPerPage);
//			System.out.println(userList.size());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
