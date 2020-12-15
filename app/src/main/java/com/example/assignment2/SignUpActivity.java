package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment2.Database.DbHandler;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
	private EditText usernameEditText;
	private EditText passwordEditText;
	private EditText addressEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup_activity);
		usernameEditText = findViewById(R.id.txtSoUsername);
		passwordEditText = findViewById(R.id.txtSoPassword);
		addressEditText = findViewById(R.id.txtSoAddress);

		Button btnSignIn = findViewById(R.id.btnRegister);
		btnSignIn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				registerDetails();
			}
		});

	}

	private void registerDetails(){
		DbHandler dbHandler = new DbHandler(SignUpActivity.this);
		HashMap<String,String> user = dbHandler.getUserByUsername(usernameEditText.getText().toString());
		if(user.size() != 0){
			Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
		} else {
			dbHandler.insertUserDetails(
					usernameEditText.getText().toString(),
					passwordEditText.getText().toString(),
					addressEditText.getText().toString()
			);
			Toast.makeText(this, "Successfully Signed Up", Toast.LENGTH_SHORT).show();
			Intent signIn = new Intent(SignUpActivity.this, LoginActivity.class);
			startActivity(signIn);
		}
	}
}
