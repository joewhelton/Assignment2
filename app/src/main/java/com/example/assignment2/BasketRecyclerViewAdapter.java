package com.example.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.HashMap;

public class BasketRecyclerViewAdapter extends RecyclerView.Adapter<BasketRecyclerViewAdapter.ViewHolder>  {
	private ArrayList<HashMap<String, String>> mData;
	private LayoutInflater mInflater;
	private ItemClickListener mClickListener;

	public BasketRecyclerViewAdapter (Context c, ArrayList<HashMap<String, String>> basketList){
		this.mInflater = LayoutInflater.from(c);
		this.mData = basketList;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view =	mInflater.inflate(R.layout.rv_basket_row, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int	position) {
		HashMap<String, String> item = mData.get(position);
		holder.productName.setText(item.get("productname"));
		holder.productCode.setText(item.get("productcode"));
		holder.productPrice.setText("€" + String.format("%.2f", Float.parseFloat(item.get("productprice"))));
	}

	@Override
	public int getItemCount() {
		return mData.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		TextView productName;
		TextView productCode;
		TextView productPrice;
		Chip deleteChip;

		ViewHolder(View itemView) {
			super(itemView);
			productName = itemView.findViewById(R.id.tvProductName);
			productCode = itemView.findViewById(R.id.tvProductCode);
			productPrice = itemView.findViewById(R.id.tvProductPrice);
			deleteChip = itemView.findViewById(R.id.deleteChip);

			deleteChip.setOnClickListener(this);
		}

		public void onClick(View view) {
			if (mClickListener != null)
				mClickListener.onItemClick(view, getAdapterPosition());
		}
	}

	// convenience method for getting data at click	position
	HashMap<String, String> getItem(int id) {
		return mData.get(id);
	}

	// allows clicks events to be caught
	void setClickListener(ItemClickListener itemClickListener) {
		this.mClickListener = itemClickListener;
	}

	// parent activity will implement this method to respond to click events
	public interface ItemClickListener {
		void onItemClick(View view, int position);
	}
}
