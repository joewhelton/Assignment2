package com.example.assignment2.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.Classes.Product;
import com.example.assignment2.ProductRecyclerViewAdapter;
import com.example.assignment2.R;

import java.util.ArrayList;

public class FragmentYeast extends Fragment {
	View view;
	ProductRecyclerViewAdapter adapter;
	ArrayList<Product> productList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			productList  = getArguments().getParcelableArrayList("data");
		}

		view = inflater.inflate(R.layout.store_fragment, container, false);
		RecyclerView recyclerView = view.findViewById(R.id.rvProducts);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		adapter = new ProductRecyclerViewAdapter(getContext(), productList);
		recyclerView.setAdapter(adapter);

		return view;
	}
}