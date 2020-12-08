package com.example.assignment2.Classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		//The parcelable object has to be the first one
		dest.writeString(this.name);
		dest.writeString(this.code);
		dest.writeDouble(this.price);
		dest.writeInt(this.imageId);
	}

	protected Product(Parcel in){
		this.name = in.readString();
		this.code = in.readString();
		this.price = in.readDouble();
		this.imageId = in.readInt();
	}

	public static final Creator<Product> CREATOR = new Creator<Product>(){
		public Product createFromParcel(Parcel source) {
			return new Product(source);
		}
		public Product[] newArray(int size){
			return new Product[size];
		}
	};

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
