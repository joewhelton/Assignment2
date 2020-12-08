package com.example.assignment2.Classes;

import android.os.Parcelable;

import java.util.ArrayList;

public class Category implements Parcelable {
	private String name;
	private ArrayList<Product> mProducts = new ArrayList<>();

	public Category(String name, ArrayList<Product> productList){
		this.name = name;
		this.mProducts
	}
}
