package com.example.assignment2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.Database.DbHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class BasketActivity extends AppCompatActivity implements BasketRecyclerViewAdapter.ItemClickListener {
	private Intent intent;
	private BasketRecyclerViewAdapter adapter;
	private ArrayList<HashMap<String, String>> basketList;
	private double basketTotal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.basket_activity);
		updateBasket();
		RecyclerView recyclerView = findViewById(R.id.rvBasket);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		adapter = new BasketRecyclerViewAdapter(this, this.basketList);
		adapter.setClickListener(this);
		recyclerView.setAdapter(adapter);

		Button btnEmptyBasket = findViewById(R.id.emptyBasket);
		btnEmptyBasket.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				emptyBasket();
			}
		});

		Button btnContinueShopping = findViewById(R.id.continueShopping);
		btnContinueShopping.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent home = new Intent(BasketActivity.this, MainActivity.class);
				startActivity(home);
			}
		});

		Button btnPayment = findViewById(R.id.goToPayment);
		btnPayment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(basketList.size() == 0){
					Toast.makeText(getApplicationContext(), "No items in basket", Toast.LENGTH_SHORT).show();
				} else {
					Intent pay = new Intent(BasketActivity.this, PaymentActivity.class);
					startActivity(pay);
				}
			}
		});
	}

	@Override
	public void onItemClick(View view, int position) {
		//Toast.makeText(this, "Deleting item " + adapter.getItem(position).get("id"), Toast.LENGTH_SHORT).show();
		DbHandler db = new DbHandler(this);
		db.deleteBasketItem(Long.parseLong(adapter.getItem(position).get("id")));
		basketList.remove(position);
		adapter.notifyItemRemoved(position);
		adapter.notifyItemRangeChanged(position, basketList.size());
		calculateBasketTotal();
	}

	private void updateBasket(){
		DbHandler db = new DbHandler(this);
		this.basketList = db.getBasketList();
		calculateBasketTotal();
	}

	private void emptyBasket(){
		DbHandler db = new DbHandler(this);
		String[] ids = new String[this.basketList.size()];
		int idIndex = 0;

		for (HashMap<String, String> item : this.basketList){
			ids[idIndex++] = item.get("id");
		}
		db.emptyBasket(ids);
		db.close();
		this.basketList.clear();
		adapter.notifyDataSetChanged();
		calculateBasketTotal();
	}

	private void calculateBasketTotal(){
		this.basketTotal = 0;
		basketList.forEach((item) -> {
			this.basketTotal += Float.parseFloat(item.get("productprice"));
		});
		TextView tvBasketTotal = findViewById(R.id.basket_total);
		tvBasketTotal.setText(String.format("Total: €%.2f", this.basketTotal));
	}

}
