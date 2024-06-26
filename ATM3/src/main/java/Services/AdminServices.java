package Services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import Database.Connector;
import Models.Transaction;
import Models.Transfer;
import Models.User;

public class AdminServices {
	Utilities utils = new Utilities();
	Connector connector = new Connector();
	
	private int numberOfRecord;
	
	public int getnumberOfRecord() { return numberOfRecord; } 
	
	public User createAccount(String name, String contactNumber, String gender, String address) throws SQLException {
		Random randomNumber = new Random();
        int idNumber = randomNumber.nextInt(Constants.ID_NUMBER_LIMIT);
        int pin = randomNumber.nextInt(Constants.PIN_NUMBER_LIMIT);
        
        if(utils.checkContactNumber(contactNumber) && utils.checkName(name)) {
        	
        	try {
        		User user = new User(utils.padLeftZeros(String.valueOf(idNumber), Constants.ID_NUMBER_LENGTH),utils.padLeftZeros(String.valueOf(pin), Constants.ID_PIN_LENGTH),name,contactNumber,gender,address,Constants.INITIAL_BALANCE,null,Constants.USER_ROLE);
        		String addUser = "INSERT INTO user_info VALUES(?,?,?,?,?,?,?,?)";
            	PreparedStatement newUserStatement = connector.getConnection().prepareStatement(addUser);
            	newUserStatement.setInt(1, Integer.parseInt(user.getIdNumber()));
            	newUserStatement.setInt(2, Integer.parseInt(user.getPin()));
            	newUserStatement.setString(3, user.getName());
            	newUserStatement.setString(4, user.getContactNumber());
            	newUserStatement.setString(5, user.getGender());
            	newUserStatement.setString(6, user.getAddress());
            	newUserStatement.setDouble(7, user.getBalance());
            	newUserStatement.setString(8, Constants.USER_ROLE);
        		newUserStatement.execute();
        		return user;
        		
        	}catch(SQLException sqlError) {
        		return null;
        	}
        }
		return null;
        
	}
	
	public List<User> accountReport() throws SQLException {
		List<User> userList = new ArrayList<>();
		String getAllUser = "SELECT SQL_CALC_FOUND_ROWS id_number FROM user_info WHERE role = 'User'";
		
		PreparedStatement getAllUserStatement = connector.getConnection().prepareStatement(getAllUser);
		
		try {
			ResultSet resultSet = getAllUserStatement.executeQuery();
		
			while(resultSet.next()) {
				userList.add(utils.getUserById(resultSet.getString(1)));
			}
		
		}catch (SQLException e) { 
            e.printStackTrace(); 
        } 
		return userList;
	}
	
	public List<Transaction> withdrawalReport(Date checkDate) throws SQLException {
		List<Transaction> withdrawalList = new ArrayList<>();
		
		String getWithdrawals ="SELECT transaction_info.transaction_id,user_info.id_number, transaction_info.transaction_date, transaction_info.amount, transaction_info.balance, transaction_info.transaction_type\r\n"
				+ "				FROM user_info\r\n"
				+ "				INNER JOIN transaction_info\r\n"
				+ "				WHERE user_info.id_number = transaction_info.id_number AND transaction_info.transaction_type LIKE 'withdraw' AND DATE(transaction_date) = ?;";
		
		PreparedStatement getWithdrawalsStatement = connector.getConnection().prepareStatement(getWithdrawals);
		getWithdrawalsStatement.setDate(1, (java.sql.Date) checkDate);
		
		ResultSet resultSet = getWithdrawalsStatement.executeQuery();
		
		while(resultSet.next()) {
			withdrawalList.add(new Transaction(resultSet.getString(1), 
					resultSet.getString(2),
					resultSet.getTimestamp(3).toLocalDateTime(), 
					resultSet.getDouble(4),
					resultSet.getDouble(5), 
					resultSet.getString(6)));
		}
		return withdrawalList;
	}
	
	public List<Transaction> depositReport(Date checkDate) throws SQLException {
		List<Transaction> depositList = new ArrayList<>();
		
		String getDeposits = "SELECT transaction_info.transaction_id,user_info.id_number, transaction_info.transaction_date, transaction_info.amount, transaction_info.balance, transaction_info.transaction_type\r\n"
				+ "FROM user_info \r\n"
				+ "INNER JOIN transaction_info\r\n"
				+ "WHERE user_info.id_number = transaction_info.id_number AND transaction_info.transaction_type LIKE 'deposit' AND DATE(transaction_date) = ?; ";
		
		PreparedStatement getDepositsStatement = connector.getConnection().prepareStatement(getDeposits);
		getDepositsStatement.setDate(1, (java.sql.Date) checkDate);
		
		ResultSet resultSet = getDepositsStatement.executeQuery();
		
		while(resultSet.next()) {
			depositList.add(new Transaction(resultSet.getString(1), 
					resultSet.getString(2),
					resultSet.getTimestamp(3).toLocalDateTime(), 
					resultSet.getDouble(4),
					resultSet.getDouble(5), 
					resultSet.getString(6)));
		}
		return depositList;
	}
	
	public List<Transfer> transferReport(Date checkDate) throws SQLException {
		List<Transfer> transferList = new ArrayList<>();
		
		String getTransfers = "SELECT sender.transaction_id, sender.transaction_date, sender.id_number, sender.balance, sender.amount, receiver.id_number, receiver.balance\r\n"
				+ "FROM transaction_info as sender\r\n"
				+ "INNER JOIN transaction_info as receiver\r\n"
				+ "ON sender.transaction_id = receiver.transaction_id and sender.transaction_type LIKE 'Transfer' and receiver.transaction_type LIKE 'Transferred'\r\n"
				+ "AND DATE(sender.transaction_date)= ?;";
		PreparedStatement getTransferStatement = connector.getConnection().prepareStatement(getTransfers);
		getTransferStatement.setDate(1, (java.sql.Date) checkDate);
		ResultSet resultSet = getTransferStatement.executeQuery();
		
		while(resultSet.next()) {
			transferList.add(new Transfer(
					resultSet.getString(1), 
					resultSet.getString(3), 
					resultSet.getTimestamp(2).toLocalDateTime(), 
					resultSet.getDouble(5), 
					resultSet.getDouble(4), 
					Constants.FUNCTION_TRANSFER_MONEY, 
					resultSet.getString(6), 
					resultSet.getDouble(7)));
		}
		return transferList;
	}
	
}
