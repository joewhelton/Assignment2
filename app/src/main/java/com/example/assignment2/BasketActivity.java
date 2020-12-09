package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.Database.DbHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class BasketActivity extends AppCompatActivity {
	Intent intent;
	BasketRecyclerViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.basket_activity);
		DbHandler db = new DbHandler(this);
		ArrayList<HashMap<String, String>> basketList = db.getBasketList();

		RecyclerView recyclerView = findViewById(R.id.rvBasket);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		adapter = new BasketRecyclerViewAdapter(this, basketList);
		recyclerView.setAdapter(adapter);
	}
}
