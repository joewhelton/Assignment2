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

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
	private EditText usernameEditText;
	private EditText passwordEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		usernameEditText = findViewById(R.id.txtSiUsername);
		passwordEditText = findViewById(R.id.txtSiPassword);

		Button btnSignUp = findViewById(R.id.btnSignUp);
		btnSignUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent signIn = new Intent(LoginActivity.this, SignUpActivity.class);
				startActivity(signIn);
			}
		});

		Button btnSignIn = findViewById(R.id.btnSignIn);
		btnSignIn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				signIn();
			}
		});
	}

	private void signIn(){
		DbHandler dbHandler = new DbHandler(LoginActivity.this);
		HashMap<String,String> user = dbHandler.getUserByUsername(usernameEditText.getText().toString());
		if(user.size() == 0){
			Toast.makeText(this, "No user found", Toast.LENGTH_SHORT).show();
		} else if(passwordEditText.getText().toString().equals(user.get("password"))) {
			Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "Signed In Successfully", Toast.LENGTH_SHORT).show();
			Intent main = new Intent(LoginActivity.this, MainActivity.class);
			SharedPreferences spref = PreferenceManager.getDefaultSharedPreferences(this);
			SharedPreferences.Editor editor = spref.edit();
			editor.putLong("id", Long.parseLong(user.get("id")));
			editor.putString("username", user.get("username"));
			editor.putString("address", user.get("address"));
			editor.apply();
			startActivity(main);
		}
	}
}
