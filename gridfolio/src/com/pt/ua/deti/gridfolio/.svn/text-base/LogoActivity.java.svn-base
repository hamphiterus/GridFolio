package com.pt.ua.deti.gridfolio;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LogoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logo_view);
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				final Intent login = new Intent(LogoActivity.this, RegisterOrLoginActivity.class);
				startActivity(login);
			}
		}, 2500);
	}

}
