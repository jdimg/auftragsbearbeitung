package model;

import javafx.scene.shape.Circle;

public class Job {

	private int customerNumber;
	private String customerName;
	private String customerNotice;
	private boolean status;		//true = Fertig oder false = Unfertig.
	private Circle circle;
	
	
	public Job(int i, String name, String notice, boolean bo, Circle c) {
		customerNumber = i;
		customerName = name;
		customerNotice = notice;
		status = bo;
		circle = c;
	}


	public Circle getCircle() {
		return circle;
	}

	public String getCustomerName() {
		return customerName;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public boolean isStatus() {
		return status;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getCustomerNotice() {
		return customerNotice;
	}

	public void setCustomerNotice(String customerNotice) {
		this.customerNotice = customerNotice;
	}
}
