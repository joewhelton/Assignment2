package com.example.assignment2.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.assignment2.Classes.Product;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentPagerAdapter {
	Context context;
	int totalTabs;
	ArrayList<Product> productList;

	public FragmentAdapter(Context c, FragmentManager fm, int totalTabs, ArrayList<Product> productList){
		super(fm);
		context = c;
		this.totalTabs = totalTabs;
		this.productList = productList;
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				FragmentMalt maltFragment = new FragmentMalt();
				return maltFragment;
			case 1:
				FragmentYeast yeastFragment = new FragmentYeast();
				Bundle bundle = new Bundle();
				bundle.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) productList);
				yeastFragment.setArguments(bundle);
				return yeastFragment;
			case 2:
				FragmentHops hopsFragment = new FragmentHops();
				return hopsFragment;
			default:
				return null;
		}
	}
	@Override
	public int getCount() {return totalTabs;}
}
