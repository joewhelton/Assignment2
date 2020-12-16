package com.example.assignment2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.assignment2.Classes.Product;
import com.example.assignment2.Classes.User;
import com.example.assignment2.Database.DbHandler;
import com.example.assignment2.Fragments.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	TabLayout tabLayout;
	ViewPager viewPager;
	ArrayList<Product> maltData;
	ArrayList<Product> yeastData;
	ArrayList<Product> hopsData;
	User signedInUser;
	SharedPreferences spref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		spref = PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor = spref.edit();
		editor.clear();
		editor.apply();

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

	@Override
	protected void onResume() {
		super.onResume();
		if(spref.contains("username")){
			long id = spref.getLong("id", 0);
			String username = spref.getString("username", null);
			String address = spref.getString("address", null);
			setSignedInUser(new User(id, username, address));
		}
	}

	public void addToCart(Product product, int tabID){
		if(tabID == viewPager.getCurrentItem()) {
			DbHandler dbHandler = new DbHandler(MainActivity.this);
			long rowId = dbHandler.insertProductDetails(product.getName(), product.getCode(), product.getPrice());
			Toast.makeText(this, "Added item code " + product.getName(), Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.sign_in:
				Intent signIn = new Intent(MainActivity.this, LoginActivity.class);
				startActivity(signIn);
				return true;
			case R.id.view_basket:
				Intent basket = new Intent(MainActivity.this, BasketActivity.class);
				startActivity(basket);
				return true;
			case R.id.go_home:

				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	public User getSignedInUser() {
		return signedInUser;
	}

	public void setSignedInUser(User signedInUser) {
		this.signedInUser = signedInUser;
		setTitle("Signed in as " + signedInUser.getUsername());
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
		yeastData.add(new Product("English Ale Yeast", "hbs20017", 4.00, R.drawable.english_ale_yeast));
		yeastData.add(new Product("Sour Lallemand Yeast", "hbs20019", 4.20, R.drawable.sour_yeast));
		yeastData.add(new Product("Saison Yeast", "hbs20026", 3.99, R.drawable.saison_yeast));
		yeastData.add(new Product("Munich Lager Yeast", "hbs20052", 4.70, R.drawable.munich_yeast));

		this.hopsData = new ArrayList<>();
		hopsData.add(new Product("Target T90 Hop Pellets", "hbs30007", 6.99, R.drawable.hop_pellets));
		hopsData.add(new Product("Citra Leaf Hops", "hbs30016", 5.50, R.drawable.hop_leaf));
		hopsData.add(new Product("Styrian Goldings Pellets", "hbs30049", 5.85, R.drawable.hop_pellets_close));
		hopsData.add(new Product("Czech Saaz leaf hops", "hbs30056", 6.75, R.drawable.hop_leaf));
		hopsData.add(new Product("Pacifica Finishing Hops", "hbs30052", 5.90, R.drawable.hop_bag));

	}
}