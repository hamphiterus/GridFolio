package com.pt.ua.deti.gridfolio;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint({ "NewApi", "CutPasteId" })
public class CreditsActivity extends Activity {

	// Declare
	private LinearLayout slidingPanel;
	private boolean isExpanded;
	private DisplayMetrics metrics;
	private int panelWidth;
	private GestureDetector gestureDetector = null;
	FrameLayout.LayoutParams menuPanelParameters;
	FrameLayout.LayoutParams slidingPanelParameters;
	LinearLayout.LayoutParams headerPanelParameters;
	LinearLayout.LayoutParams listViewParameters;
	@SuppressWarnings("unused")
	private int flag = 0;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_credits);

		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		bar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

		findViewById(R.id.button_perfil).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						// attemptLogin(); 
						startActivity(new Intent(getApplicationContext(), PerfilActivity.class));
					}
				});

		findViewById(R.id.button_eventos).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						// attemptLogin();
						startActivity(new Intent(getApplicationContext(), EventsActivity.class));
					}
				});

		findViewById(R.id.button_mapa).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						// attemptLogin();
						startActivity(new Intent(getApplicationContext(), MapActivity.class));
					}
				});

		findViewById(R.id.button_projetos).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						// attemptLogin();
						startActivity(new Intent(getApplicationContext(), VerProjetoActivity.class));
					}
				});

		findViewById(R.id.button_credits).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						// attemptLogin();
						startActivity(new Intent(getApplicationContext(),CreditsActivity.class));
					}
				});

		findViewById(R.id.button_projetos).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						// attemptLogin();
						startActivity(new Intent(getApplicationContext(), ShowMyProjectsActivity.class));
					}
				});

		gestureDetector = new GestureDetector(new MyGestureDetector());
		// Initialize
		metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		panelWidth = (int) ((metrics.widthPixels) * 0.75);

		slidingPanel = (LinearLayout) findViewById(R.id.slidingPanel);
		slidingPanelParameters = (FrameLayout.LayoutParams) slidingPanel
				.getLayoutParams();
		slidingPanelParameters.width = metrics.widthPixels;
		slidingPanel.setLayoutParams(slidingPanelParameters);

		View mainview = (View) findViewById(R.id.slidingPanel);

		// Set the touch listener for the main view to be our custom gesture
		// listener
		mainview.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (gestureDetector.onTouchEvent(event)) {
					return true;
				}
				return false;
			}
		});

		// Slide the Panel
		View itemid = findViewById(android.R.id.home);
		itemid.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (!isExpanded) {
					flag = 1;
					isExpanded = true;

					// Expand
					new ExpandAnimation(slidingPanel, panelWidth,
							Animation.RELATIVE_TO_SELF, 0.0f,
							Animation.RELATIVE_TO_SELF, 0.75f, 0, 0.0f, 0, 0.0f);
				} else {
					isExpanded = false;
					flag = 0;
					// Collapse
					new CollapseAnimation(slidingPanel, panelWidth,
							TranslateAnimation.RELATIVE_TO_SELF, 0.75f,
							TranslateAnimation.RELATIVE_TO_SELF, 0.0f, 0, 0.0f,
							0, 0.0f);
				}
			}
		});

	}

	public void toTheWebDeti(View view) {
		String url = "http://www.ua.pt/deti/";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}

	public void toTheWebDeca(View view) {
		String url = "http://www.ua.pt/deca/";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
}
