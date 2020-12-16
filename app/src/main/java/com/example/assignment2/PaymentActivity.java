package com.example.assignment2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment2.Classes.User;
import com.example.assignment2.Database.DbHandler;

public class PaymentActivity  extends AppCompatActivity {
	private EditText paymentNameEditText;
	private EditText paymentAddressEditText;
	private EditText paymentCardEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.payment_activity);
		paymentNameEditText = findViewById(R.id.paymentNameEditText);
		paymentAddressEditText = findViewById(R.id.paymentAddressEditText);
		paymentCardEditText = findViewById(R.id.paymentCardEditText);

		SharedPreferences spref = PreferenceManager.getDefaultSharedPreferences(this);
		if(spref.contains("address")){
			String address = spref.getString("address", null);
			paymentAddressEditText.setText(address);
		}

		Button btnSignIn = findViewById(R.id.btnPay);
		btnSignIn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				confirmPayment();
			}
		});
	}

	private void confirmPayment(){
		if(paymentNameEditText.getText().toString().equals("")){
			Toast.makeText(this, "Enter a shipping name", Toast.LENGTH_SHORT).show();
		} else if(paymentAddressEditText.getText().toString().equals("")){
			Toast.makeText(this, "Enter a shipping address", Toast.LENGTH_SHORT).show();
		} else if(paymentCardEditText.getText().toString().equals("")){
			Toast.makeText(this, "Enter a card number", Toast.LENGTH_SHORT).show();
		} else {
			DbHandler db = new DbHandler(this);
			Toast.makeText(this, "Payment Confirmed!", Toast.LENGTH_SHORT).show();
			db.clearAllBasketItems();
			Intent main = new Intent(PaymentActivity.this, MainActivity.class);
			startActivity(main);
		}
	}
}
