package Models;

import java.time.LocalDateTime;

public class Transfer extends Transaction{
	private String receiverID;
	private double receiverBalance;
	
	public Transfer() {
		super();
	}
	public Transfer(String transactionID, String idNumber, LocalDateTime date, double amount, double balance,
			String type, String receiverID, double receiverBalance) {
		super(transactionID, idNumber, date, amount, balance, type);
		this.receiverID = receiverID;
		this.receiverBalance = receiverBalance;
	}
	public String getReceiverID() {
		return receiverID;
	}
	public void setReceiverID(String receiverID) {
		this.receiverID = receiverID;
	}
	public double getReceiverBalance() {
		return receiverBalance;
	}
	public void setReceiverBalance(double receiverBalance) {
		this.receiverBalance = receiverBalance;
	}
	
	
}
