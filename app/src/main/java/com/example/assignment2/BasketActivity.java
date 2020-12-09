package com.example.assignment2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.Database.DbHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class BasketActivity extends AppCompatActivity {
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
		recyclerView.setAdapter(adapter);

		Button btnEmptyBasket = findViewById(R.id.emptyBasket);
		btnEmptyBasket.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				emptyBasket();
			}
		});
	}

	private void updateBasket(){
		DbHandler db = new DbHandler(this);
		this.basketList = db.getBasketList();
		this.basketTotal = 0;
		basketList.forEach((item) -> {
			this.basketTotal += Float.parseFloat(item.get("productprice"));
		});
		DecimalFormat df = new DecimalFormat("###.##");
		TextView tvBasketTotal = findViewById(R.id.basket_total);
		tvBasketTotal.setText("Total: €" + df.format(this.basketTotal));
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
		updateBasket();
	}
}
