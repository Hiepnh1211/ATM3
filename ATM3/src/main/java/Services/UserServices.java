package Services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import Database.Connector;
import Models.Transaction;
import Models.User;

public class UserServices {
	Connector connector = new Connector();
	Utilities utils = new Utilities();
	
	public boolean checkPassword(String userId, String password) {
		try {
			String getUserPin = "SELECT pin FROM user_info WHERE id_number = ? AND pin = ?";
			PreparedStatement getPinStatement = connector.getConnection().prepareStatement(getUserPin);
			getPinStatement.setString(1, userId);
			getPinStatement.setString(2, password);
			ResultSet rs = getPinStatement.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(SQLException sqlError) {
			return false;
		}
		return false;
	}
	
	public boolean checkTransactionLimit(String userId, String transactionType) throws SQLException {
		String checkTransactionLimit = "SELECT COUNT(transaction_id) FROM atm.transaction_info "
				+ "WHERE DATE(transaction_date) = (SELECT CURRENT_DATE()) "
				+ "AND id_number = ? "
				+ "AND transaction_type = ?" ;
		
		PreparedStatement checkTransactionLimitStatement = connector.getConnection().prepareStatement(checkTransactionLimit);
		checkTransactionLimitStatement.setString(1, userId);
		checkTransactionLimitStatement.setString(2, transactionType);
		
		ResultSet resultSet = checkTransactionLimitStatement.executeQuery();
		
		if(resultSet.next()) {
			int number = resultSet.getInt(1);
			if(number < Constants.MAXIMUM_DEPOSIT_WITHDRAW_TIME) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}
	
	public void balanceUpdate(User user) throws SQLException {
		String balanceUpdate = "UPDATE user_info SET balance = ? WHERE id_number = ?";
		PreparedStatement balanceUpdateStatement = connector.getConnection().prepareStatement(balanceUpdate);
		balanceUpdateStatement.setDouble(1, user.getBalance());
		balanceUpdateStatement.setString(2, user.getIdNumber());
		balanceUpdateStatement.execute();
	}
	
	public void transactionMaking(String transactionID, User user, String type, LocalDateTime transactionDate, Double amount) throws SQLException {
		String depositMoney = "INSERT INTO transaction_info(transaction_id, id_number, transaction_type, transaction_date, amount, balance) VALUES (?,?,?,?,?,?)";
		PreparedStatement depositStatement = connector.getConnection().prepareStatement(depositMoney);
		depositStatement.setString(1, transactionID);
		depositStatement.setInt(2, Integer.parseInt(user.getIdNumber()));
		depositStatement.setString(3, type);
		depositStatement.setTimestamp(4, Timestamp.valueOf(transactionDate));
		depositStatement.setDouble(5, amount);
		depositStatement.setDouble(6, user.getBalance());
		
		depositStatement.execute();
	}
	
	public Transaction deposit(User user, double amount) throws SQLException {
		
		if(amount > Constants.MAXIMUM_TRANSACTION_AMOUNT || amount <= 0 || !checkTransactionLimit(user.getIdNumber(), Constants.FUNCTION_DEPOSIT)) {
			return null;
		}else {
			user.setBalance(user.getBalance() + amount);
			
			balanceUpdate(user);
			
			String transactionId = utils.transactionIdGenerator(user.getIdNumber(),LocalDateTime.now().toString() , Constants.FUNCTION_DEPOSIT);
			LocalDateTime transactionDate = LocalDateTime.now();
			
			Transaction transaction = new Transaction(transactionId, user.getIdNumber(), transactionDate, amount, user.getBalance(), Constants.TRANSACTION_TYPE_DEPOSIT);
			
			transactionMaking(transactionId, user, Constants.TRANSACTION_TYPE_DEPOSIT, transactionDate, amount);
			
			return transaction;
		}
	}
	
	public Transaction withdraw(User user, double amount) throws SQLException {
		
		if(amount > Constants.MAXIMUM_TRANSACTION_AMOUNT || amount > user.getBalance() || amount <= 0 || !checkTransactionLimit(user.getIdNumber(), Constants.FUNCTION_WITHDRAW)) {
			return null;
		}else {
			user.setBalance(user.getBalance() - amount);
			
			balanceUpdate(user);
			
			String transactionId = utils.transactionIdGenerator(user.getIdNumber(),LocalDateTime.now().toString() , Constants.FUNCTION_WITHDRAW);
			LocalDateTime transactionDate = LocalDateTime.now();
			
			Transaction transaction = new Transaction(transactionId, user.getIdNumber(), transactionDate, amount, user.getBalance(), Constants.TRANSACTION_TYPE_WITHDRAW);
			
			transactionMaking(transactionId, user, Constants.TRANSACTION_TYPE_WITHDRAW, transactionDate, amount);
			return transaction;
		}
	}
	
	public Transaction transferMoney(User sender, User receiver, double amount) throws SQLException {
		
		String transferId = utils.transactionIdGenerator(sender.getIdNumber(), LocalDateTime.now().toString(), Constants.FUNCTION_TRANSFER_MONEY);
		LocalDateTime transactionDate = LocalDateTime.now();
		
			sender.setBalance(sender.getBalance() - amount);
			receiver.setBalance(receiver.getBalance() + amount);
			
			try {
				balanceUpdate(sender);
				
				balanceUpdate(receiver);
				
				Transaction send = new Transaction(transferId, sender.getIdNumber(), transactionDate, amount, sender.getBalance(), Constants.FUNCTION_TRANSFER_MONEY);
				
				transactionMaking(transferId, sender, Constants.FUNCTION_TRANSFER_MONEY, transactionDate, amount);
				
				transactionMaking(transferId, receiver, Constants.FUNCTION_RECEIVE_MONEY, transactionDate, amount);
				
				return send;
				
			}catch (SQLException e) {

				return null;
			}
			
	}
	
	public boolean changePassword(User user, String oldPassword, String newPassword, String confirmNewPassword) throws SQLException {
		if(!newPassword.equals(confirmNewPassword) || !oldPassword.equals(user.getPin()) || oldPassword.equals("") || newPassword.equals("") || confirmNewPassword.equals("")) {
			return false;
		}else {
			user.setPin(newPassword);
			String passwordUpdate = "UPDATE user_info SET pin = ? where id_number = ?";
			PreparedStatement passwordUpdateStatement = connector.getConnection().prepareStatement(passwordUpdate);
			passwordUpdateStatement.setString(1, newPassword);
			passwordUpdateStatement.setString(2, user.getIdNumber());
			
			passwordUpdateStatement.execute();
			return true;
		}
		
	}
	
}
