package com.example.assignment2.Classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Category implements Parcelable {
	private String name;
	private ArrayList<Product> mProducts = new ArrayList<>();

	public Category(String name, ArrayList<Product> productList){
		this.name = name;
		this.mProducts = productList;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.name);
		dest.writeTypedList(this.mProducts);
	}

	protected Category(Parcel in){
		this.name = in.readString();
		in.readTypedList(this.mProducts, Product.CREATOR);
	}

	public static final Creator<Category> CREATOR = new Creator<Category>(){
		public Category createFromParcel(Parcel source){
			return new Category(source);
		}
		public Category[] newArray(int size){
			return new Category[size];
		}
	};
}
