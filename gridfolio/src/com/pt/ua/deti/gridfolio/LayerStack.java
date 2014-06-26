package com.pt.ua.deti.gridfolio;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.pt.ua.deti.gridfolio.threadsEvents.ThreadEventAmanha;
import com.pt.ua.deti.gridfolio.threadsEvents.ThreadEventBreve;
import com.pt.ua.deti.gridfolio.threadsEvents.ThreadEventHoje;

@SuppressLint("CutPasteId")
public class LayerStack extends Activity {

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
	private GridView gv;
	private GridView gv1;
	private GridView gv2;
	private ProgressDialog pd;
	private MyAdapter myAdapterGv;
	private MyAdapter myAdapterGv1;
	private MyAdapter myAdapterGv2;

	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layer_stack);
		//
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		bar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		
		pd = new ProgressDialog(LayerStack.this);
		pd.setTitle("A carregar eventos");
		pd.show();
		pd.setCancelable(true);
		
		runThreads();

		gv = (GridView) findViewById(R.id.gridView);
		myAdapterGv = new MyAdapter(getApplicationContext(), 1);
		gv.setAdapter(myAdapterGv);

		// Set on item click listener to the ListView
		gv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
				Intent goEventInfo = new Intent(getApplicationContext(), EventsActivity.class);
				goEventInfo.putExtra("ID_EVENTO", pos);
				startActivity(goEventInfo);
			}
		});
		gv1 = (GridView) findViewById(R.id.gridView2);

		// Set an Adapter to the ListView
		myAdapterGv1 = new MyAdapter(getApplicationContext(), 2);
		gv1.setAdapter(myAdapterGv1);

		// Set on item click listener to the ListView
		gv1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
				Intent goEventInfo = new Intent(getApplicationContext(), EventsActivity.class);
				goEventInfo.putExtra("ID_EVENTO", pos);
				startActivity(goEventInfo);
			}
		});

		gv2 = (GridView) findViewById(R.id.gridView3);

		// Set an Adapter to the ListView
		myAdapterGv2 = new MyAdapter(getApplicationContext(), 3);
		gv2.setAdapter(myAdapterGv2);

		// Set on item click listener to the ListView
		gv2.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos,
					long id) {
				Intent goEventInfo = new Intent(getApplicationContext(),
						EventsActivity.class);
				goEventInfo.putExtra("ID_EVENTO",pos 
						);
				startActivity(goEventInfo);
			}
		});

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
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_layer_stack, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
	
	public void runThreads() {
		ThreadEventHoje threadEventHoje = new ThreadEventHoje("EventHoje");	
		ThreadEventAmanha threadEventAmanha = new ThreadEventAmanha("EventsAmanha");	
		ThreadEventBreve threadEventBreve = new ThreadEventBreve("EventsBreve");
		
		threadEventAmanha.setName("EventsAmanha");
		threadEventHoje.setName("EventHoje");
		threadEventBreve.setName("EventsBreve");
		
		threadEventAmanha.start();
		threadEventHoje.start();
		threadEventBreve.start();
		
		try {
			threadEventHoje.join(2500);
			threadEventBreve.join(2500);
			threadEventAmanha.join(2500);
			myAdapterGv.notifyDataSetChanged();
			myAdapterGv1.notifyDataSetChanged();
			myAdapterGv2.notifyDataSetChanged();
			gv.setAdapter(myAdapterGv);
			gv1.setAdapter(myAdapterGv1);
			gv2.setAdapter(myAdapterGv2);
			pd.dismiss();
		}
		catch(Exception e) {
			pd.dismiss();
		}
	}
}