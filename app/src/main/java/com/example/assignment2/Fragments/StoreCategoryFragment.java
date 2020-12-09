package com.example.assignment2.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.Classes.Product;
import com.example.assignment2.MainActivity;
import com.example.assignment2.ProductRecyclerViewAdapter;
import com.example.assignment2.R;

import java.util.ArrayList;

public class StoreCategoryFragment extends Fragment {
	int tabID;
	View view;
	ProductRecyclerViewAdapter adapter;
	ArrayList<Product> productList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			productList  = getArguments().getParcelableArrayList("data");
			tabID = getArguments().getInt("tabID");
		}

		view = inflater.inflate(R.layout.store_fragment, container, false);
		RecyclerView recyclerView = view.findViewById(R.id.rvProducts);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		adapter = new ProductRecyclerViewAdapter(getContext(), productList);
		recyclerView.setAdapter(adapter);
		registerForContextMenu(recyclerView);
		return view;
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		int position = -1;
		try {
			position = adapter.getPosition();
		} catch (Exception e) {
			return super.onContextItemSelected(item);
		}

		switch (item.getItemId()) {
			case R.id.addToCart:
				((MainActivity)getActivity()).addToCart(adapter.getProductByPosition(position), tabID);
				break;
			case R.id.addToWishlist:
				// do your stuff
				break;
		}
		return super.onContextItemSelected(item);
	}
}
