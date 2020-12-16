package com.example.assignment2;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.Classes.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder>{
	private LayoutInflater mInflater;
	private ArrayList<Product> productList;
	//private ItemClickListener mClickListener;
	private int position;

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
	public void onViewRecycled(ViewHolder holder) {
		holder.itemView.setOnLongClickListener(null);
		super.onViewRecycled(holder);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int	position) {
		holder.productName.setText(productList.get(position).getName());
		holder.productCode.setText(String.format("Code: %s", productList.get(position).getCode()));
		holder.productPrice.setText(String.format("€%.2f", productList.get(position).getPrice()));
		holder.productImage.setImageResource(productList.get(position).getImageId());
		holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
			@Override
			public boolean onLongClick(View v){
				setPosition(holder.getPosition());
				return false;
			}
		});
	}

	@Override
	public int getItemCount() {
		return productList.size();
	}

	public class ViewHolder extends	RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
		TextView productName;
		TextView productCode;
		TextView productPrice;
		ImageView productImage;
		ViewHolder(View itemView) {
			super(itemView);
			productName = itemView.findViewById(R.id.tvProductName);
			productCode = itemView.findViewById(R.id.tvProductCode);
			productPrice = itemView.findViewById(R.id.tvProductPrice);
			productImage = itemView.findViewById(R.id.imgProduct);
			itemView.setOnCreateContextMenuListener(this);
		}
		@Override
		public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
			menu.add(Menu.NONE, R.id.addToCart, Menu.NONE, "Add To Cart");
			menu.add(Menu.NONE, R.id.addToWishlist, Menu.NONE, "Add To Wishlist");
		}
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Product getProductByPosition(int position){
		return productList.get(position);
	}
}
