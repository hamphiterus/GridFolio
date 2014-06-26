package com.pt.ua.deti.gridfolio;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
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

import com.pt.ua.deti.gridfolio.singleton.AppDataProject;

@SuppressLint("CutPasteId")
public class VerProjetoActivity extends Activity {
	@SuppressWarnings("unused")
	private int flag = 0;
	private LinearLayout slidingPanel;
	private boolean isExpanded;
	private int panelWidth;
	private GestureDetector gestureDetector = null;;
	private DisplayMetrics metrics;
	FrameLayout.LayoutParams slidingPanelParameters;
	FrameLayout.LayoutParams menuPanelParameters;
	LinearLayout.LayoutParams headerPanelParameters;
	LinearLayout.LayoutParams listViewParameters;
	private TextView titulo;
	private TextView disciplina;
	private TextView data;
	private TextView professores;
	private TextView descricao;
	private int id;
	PagerContainer mContainer;
	
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ver_projeto);
		
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		bar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		
//		InsertImageProjeto insertImageProjeto = new InsertImageProjeto();
//		try {
//			insertImageProjeto.execute().get();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Bundle bd = getIntent().getExtras();
		id = bd.getInt("ID_PROJETO");

		data = (TextView) findViewById(R.id.data);
		titulo = (TextView) findViewById(R.id.titulo);
		disciplina = (TextView) findViewById(R.id.disciplina);
		professores = (TextView) findViewById(R.id.professores);
		descricao = (TextView) findViewById(R.id.descricao);

		data.setText(AppDataProject.getInstance().getDate(id));
		titulo.setText(AppDataProject.getInstance().gettitulo(id));
		professores.setText(AppDataProject.getInstance().getProfessores(id));
		descricao.setText(AppDataProject.getInstance().getDescricao(id));
		disciplina.setText(AppDataProject.getInstance().getDisciplina(id));
		
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
					flag = 1;
					isExpanded = true;

					// Expand
					new ExpandAnimation(slidingPanel, panelWidth,
							Animation.RELATIVE_TO_SELF, 0.0f,
							Animation.RELATIVE_TO_SELF, 0.75f, 0, 0.0f, 0, 0.0f);
				} else {
					isExpanded = false;
					flag =0;
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
		case R.id.perfilProjeto:
			Intent goEdit = new Intent(VerProjetoActivity.this, EditProjectActivity.class);
			goEdit.putExtra("ID", id);
			startActivity(goEdit);
			break;
		}

		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ver_projeto, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

}
