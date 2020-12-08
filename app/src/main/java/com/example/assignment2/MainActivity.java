package com.example.assignment2;

import android.os.Bundle;
import android.widget.Toast;

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
	ArrayList<Product> maltData;
	ArrayList<Product> yeastData;
	ArrayList<Product> hopsData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tabLayout = findViewById(R.id.navTabs);
		viewPager = findViewById(R.id.viewPager);

		mockData();

		tabLayout.addTab(tabLayout.newTab().setText("Malt"));
		tabLayout.addTab(tabLayout.newTab().setText("Yeast"));
		tabLayout.addTab(tabLayout.newTab().setText("Hops"));
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

		final FragmentAdapter fragmentAdapter = new FragmentAdapter(
			this, getSupportFragmentManager(),
			tabLayout.getTabCount(),
			maltData,
			yeastData,
			hopsData
		);
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

	public void addToCart(Product product){
		Toast.makeText(this, "Adding item code " + product.getCode(), Toast.LENGTH_SHORT).show();
	}

	private void mockData(){
		this.maltData = new ArrayList<>();
		maltData.add(new Product("Pilsner Malt", "hbs10012", 2.99, R.drawable.light_malt));
		maltData.add(new Product("Roasted Malt", "hbs10017", 2.50, R.drawable.dark_malt));
		maltData.add(new Product("Munich Malt Type 2", "hbs10042", 3.25, R.drawable.munich_malt));
		maltData.add(new Product("Toasted Wheat Malt", "hbs10027", 3.45, R.drawable.wheat_malt));
		maltData.add(new Product("Rye Malt Light Roast", "hbs10022", 3.85, R.drawable.rye_malt));

		this.yeastData = new ArrayList<>();
		yeastData.add(new Product("M84 Lager Yeast", "hbs20015", 4.50, R.drawable.lager_yeast));
		yeastData.add(new Product("English Ale Yeast", "hbs20017", 4.00, R.drawable.lager_yeast));
		yeastData.add(new Product("Sour Lallemand Yeast", "hbs20019", 4.20, R.drawable.lager_yeast));
		yeastData.add(new Product("Saison Yeast", "hbs20026", 3.99, R.drawable.lager_yeast));
		yeastData.add(new Product("Munich Lager Yeast", "hbs20052", 4.70, R.drawable.lager_yeast));

		this.hopsData = new ArrayList<>();
		hopsData.add(new Product("Target T90 Hop Pellets", "hbs30007", 6.99, R.drawable.hop_pellets));
		hopsData.add(new Product("Citra Leaf Hops", "hbs30016", 5.50, R.drawable.hop_leaf));
		hopsData.add(new Product("Styrian Goldings Pellets", "hbs30049", 5.85, R.drawable.hop_pellets));
		hopsData.add(new Product("Czech Saaz leaf hops", "hbs30056", 6.75, R.drawable.hop_leaf));
		hopsData.add(new Product("Pacifica (New Zealand) Finishing Hops", "hbs30052", 5.90, R.drawable.hop_pellets));

	}
}