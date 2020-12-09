package com.example.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class BasketRecyclerViewAdapter extends RecyclerView.Adapter<BasketRecyclerViewAdapter.ViewHolder>  {
	private ArrayList<HashMap<String, String>> mData;
	private LayoutInflater mInflater;

	public BasketRecyclerViewAdapter (Context c, ArrayList<HashMap<String, String>> basketList){
		this.mInflater = LayoutInflater.from(c);
		this.mData = basketList;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view =	mInflater.inflate(R.layout.rv_products_row, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int	position) {
		HashMap<String, String> item = mData.get(position);
		holder.productName.setText(item.get("productname"));
	}

	@Override
	public int getItemCount() {
		return mData.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		TextView productName;

		ViewHolder(View itemView) {
			super(itemView);
			productName = itemView.findViewById(R.id.tvProductName);
		}
	}
}
