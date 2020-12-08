package com.example.assignment2.Classes;

public class Product {
	private String name;
	private String code;
	private double price;
	private int imageId;

	public Product (String name, String code, double price, int imageId){
		this.name = name;
		this.code = code;
		this.price = price;
		this.imageId = imageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
}
