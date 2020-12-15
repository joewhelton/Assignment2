package com.example.assignment2.Classes;

public class User {
	private long dbID;
	private String username;
	private String address;

	public User(long dbID, String username, String address) {
		this.dbID = dbID;
		this.username = username;
		this.address = address;
	}

	public User(String username, String address) {
		this.username = username;
		this.address = address;
	}

	public long getDbID() {
		return dbID;
	}

	public void setDbID(long dbID) {
		this.dbID = dbID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
