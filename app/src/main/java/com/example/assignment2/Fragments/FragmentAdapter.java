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
	ArrayList<Product> maltData;
	ArrayList<Product> yeastData;
	ArrayList<Product> hopsData;

	public FragmentAdapter(Context c, FragmentManager fm, int totalTabs, ArrayList<Product> maltData, ArrayList<Product> yeastData, ArrayList<Product> hopsData){
		super(fm);
		context = c;
		this.totalTabs = totalTabs;
		this.maltData = maltData;
		this.yeastData = yeastData;
		this.hopsData = hopsData;
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				FragmentMalt maltFragment = new FragmentMalt();
				Bundle maltBundle = new Bundle();
				maltBundle.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) maltData);
				maltFragment.setArguments(maltBundle);
				return maltFragment;
			case 1:
				FragmentYeast yeastFragment = new FragmentYeast();
				Bundle yeastBundle = new Bundle();
				yeastBundle.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) yeastData);
				yeastFragment.setArguments(yeastBundle);
				return yeastFragment;
			case 2:
				FragmentHops hopsFragment = new FragmentHops();
				Bundle hopsBundle = new Bundle();
				hopsBundle.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) hopsData);
				hopsFragment.setArguments(hopsBundle);
				return hopsFragment;
			default:
				return null;
		}
	}
	@Override
	public int getCount() {return totalTabs;}
}
