package com.example.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.Classes.Product;

import java.util.ArrayList;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder>{
	private LayoutInflater mInflater;
	private ArrayList<Product> productList;
	private ItemClickListener mClickListener;

	public ProductRecyclerViewAdapter(Context context, ArrayList<Product> productList){
		this.mInflater = LayoutInflater.from(context);
		this.productList = productList;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view =	mInflater.inflate(R.layout.rv_products_row, parent,false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int	position) {
		holder.myTextView.setText(productList.get(position).getName());
		holder.myImage.setImageResource(productList.get(position).getImageId());
	}

	@Override
	public int getItemCount() {
		return productList.size();
	}

	public class ViewHolder extends	RecyclerView.ViewHolder implements View.OnClickListener {
		TextView myTextView;
		ImageView myImage;
		ViewHolder(View itemView) {
			super(itemView);
			myTextView = itemView.findViewById(R.id.tvProductName);
			myImage = itemView.findViewById(R.id.imgProduct);
			itemView.setOnClickListener(this);
		}
		@Override
		public void onClick(View view) {
			if (mClickListener != null)
				mClickListener.onItemClick(view, getAdapterPosition());
		}
	}

	void setClickListener(ItemClickListener itemClickListener) {
		this.mClickListener = itemClickListener;
	}

	public interface ItemClickListener {
		void onItemClick(View view, int position);
	}
}
