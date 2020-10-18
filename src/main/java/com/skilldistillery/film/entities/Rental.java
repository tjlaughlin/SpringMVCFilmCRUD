package com.skilldistillery.film.entities;

import java.time.LocalDate;

public class Rental {
	
	private LocalDate rentalDate;
	private String customerName;
	private String storeLocation;
	
	public LocalDate getRentalDate() {
		return rentalDate;
	}
	
	public void setRentalDate(LocalDate rentalDate) {
		this.rentalDate = rentalDate;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getStoreLocation() {
		return storeLocation;
	}

	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}
	
}
