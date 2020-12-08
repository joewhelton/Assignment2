package com.example.assignment2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.assignment2.Classes.Product;
import com.example.assignment2.Fragments.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	TabLayout tabLayout;
	ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ArrayList<Product> productList = new ArrayList<>();
		productList.add(new Product("Yeast 1", "hfhghghgh", 10.99, R.drawable.light_malt));
		productList.add(new Product("Yeast 2", "fdsfgdf", 1.99, R.drawable.light_malt));
		productList.add(new Product("Yeast 3", "hfhguilktuil", 12.99, R.drawable.light_malt));

		tabLayout = findViewById(R.id.navTabs);
		viewPager = findViewById(R.id.viewPager);

		tabLayout.addTab(tabLayout.newTab().setText("Malt"));
		tabLayout.addTab(tabLayout.newTab().setText("Yeast"));
		tabLayout.addTab(tabLayout.newTab().setText("Hops"));
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

		final FragmentAdapter fragmentAdapter = new FragmentAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount(), productList);
		viewPager.setAdapter(fragmentAdapter);
		viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				viewPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		});
	}

//	private void loadFragment(Fragment fragment) {
//		// create a FragmentManager
//		FragmentManager fm = getSupportFragmentManager();
//		// create a FragmentTransaction to begin the transaction and replace the Fragment
//		FragmentTransaction fragmentTransaction = fm.beginTransaction();
//		// replace the FrameLayout with new Fragment
//		fragmentTransaction.replace(R.id.frameLayout, fragment);
//		fragmentTransaction.commit(); // save the changes
//	}
}