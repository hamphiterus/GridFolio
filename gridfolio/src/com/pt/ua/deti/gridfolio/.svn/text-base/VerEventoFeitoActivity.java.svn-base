package com.pt.ua.deti.gridfolio;

import com.pt.ua.deti.gridfolio.singleton.AppDataAllEvents;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint({ "CutPasteId", "NewApi" })
public class VerEventoFeitoActivity extends Activity {
	private LinearLayout slidingPanel;
	private boolean isExpanded;
	private int panelWidth;
	private GestureDetector gestureDetector = null;;
	private DisplayMetrics metrics;
	FrameLayout.LayoutParams slidingPanelParameters;
	FrameLayout.LayoutParams menuPanelParameters;
	LinearLayout.LayoutParams headerPanelParameters;
	LinearLayout.LayoutParams listViewParameters;
	AlertDialog.Builder dialog;
	private TextView data, categoria, local, descricao, info;
	PagerContainer mContainer;
	int id;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ver_evento_feito);
		
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		bar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		Bundle bd = getIntent().getExtras();
		id = bd.getInt("ID_EVENTO");
		
		data = (TextView)findViewById(R.id.datafeito);
		categoria = (TextView)findViewById(R.id.categoriafeito); 
		local = (TextView)findViewById(R.id.localfeito);
		descricao = (TextView)findViewById(R.id.descricaofeito);
		info = (TextView)findViewById(R.id.maisfeito);
		
		data.setText(AppDataAllEvents.getInstance().getDate(id));
		categoria.setText(AppDataAllEvents.getInstance().getCategoria(id));
		local.setText(AppDataAllEvents.getInstance().getLocal(id));
		descricao.setText(AppDataAllEvents.getInstance().getDescricao(id));
		info.setText(AppDataAllEvents.getInstance().getMaisinfo(id));
		info.setMovementMethod(LinkMovementMethod.getInstance());
		
		mContainer = (PagerContainer)findViewById(R.id.pager_container);
		
		ViewPager viewPager = mContainer.getViewPager();
	    ProjectAdapter adapter = new ProjectAdapter(this);
	    viewPager.setAdapter(adapter);
	    viewPager.setOffscreenPageLimit(adapter.getCount());
		//A little space between pages
		viewPager.setPageMargin(10);
		   
		//If hardware acceleration is enabled, you should also remove
		// clipping on the pager for its children.
		viewPager.setClipChildren(false);
		
		findViewById(R.id.button_perfil).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {					 
						startActivity(new Intent(getApplicationContext(), PerfilActivity.class));
					}
				});

		findViewById(R.id.button_eventos).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {					
						startActivity(new Intent(getApplicationContext(), EventsActivity.class));
					}
				});

		findViewById(R.id.button_mapa).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {	
						startActivity(new Intent(getApplicationContext(), MapActivity.class));
					}
				});

		findViewById(R.id.button_projetos).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						startActivity(new Intent(getApplicationContext(), VerProjetoActivity.class));
					}
				});

		findViewById(R.id.button_credits).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						startActivity(new Intent(getApplicationContext(),CreditsActivity.class));
					}
				});

		findViewById(R.id.button_projetos).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
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
					isExpanded = true;

					// Expand
					new ExpandAnimation(slidingPanel, panelWidth,
							Animation.RELATIVE_TO_SELF, 0.0f,
							Animation.RELATIVE_TO_SELF, 0.75f, 0, 0.0f, 0, 0.0f);
				} else {
					isExpanded = false;
					// Collapse
					new CollapseAnimation(slidingPanel, panelWidth,
							TranslateAnimation.RELATIVE_TO_SELF, 0.75f,
							TranslateAnimation.RELATIVE_TO_SELF, 0.0f, 0, 0.0f,
							0, 0.0f);

				}
			}
		});
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.edit:
			Intent goEditEvent = new Intent(getApplicationContext(), EditEventActivity.class);
			goEditEvent.putExtra("ID", AppDataAllEvents.getInstance().getIDEvent(id));
			startActivity(goEditEvent);
			break;
		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ver_evento_feito, menu);
		return true;
	}

}
