package com.pt.ua.deti.gridfolio;

import java.util.concurrent.ExecutionException;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.Toast;

import com.pt.ua.deti.gridfolio.services.ServiceGetInformationUser;
import com.pt.ua.deti.gridfolio.services.ServiceGetNumberEvents;
import com.pt.ua.deti.gridfolio.singleton.AppDataProject;
import com.pt.ua.deti.gridfolio.singleton.AppDataUser;

@SuppressLint({ "SdCardPath", "CutPasteId" })
public class PerfilActivity extends Activity {
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

	private TextView curso;
	private TextView ano;
	private TextView data;
	private TextView neventos;
	private TextView nproj;
	private TextView name;

	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("SdCardPath")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perfil);
		
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		bar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

		ServiceGetInformationUser serviceGetInformationUser = new ServiceGetInformationUser();
		try {
			@SuppressWarnings("unused")
			String b = serviceGetInformationUser.execute().get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		curso = (TextView) findViewById(R.id.curso);
		ano = (TextView) findViewById(R.id.ano);
		data = (TextView) findViewById(R.id.data);
		neventos = (TextView) findViewById(R.id.neventos);
		nproj = (TextView) findViewById(R.id.nprojetos);
		name = (TextView) findViewById(R.id.namePerson);

		ServiceGetNumberEvents serviceGetNumberEvents = new ServiceGetNumberEvents();
		serviceGetNumberEvents.execute("ab", "ab", "ab");
		
		ano.setText(AppDataUser.getInstance().getAno());
		data.setText(AppDataUser.getInstance().getData());
		curso.setText(AppDataUser.getInstance().getCourse());
		neventos.setText(String.valueOf(AppDataProject.getInstance().getNumbProjetos()));
		nproj.setText(String.valueOf(AppDataProject.getInstance().getNumbProjetos()));
		name.setText(String.valueOf(AppDataUser.getInstance().getName()));	
		
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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.perfilExport:
			new GetTask(this).execute(AppDataUser.getInstance().getId());
			break;
		case R.id.perfilEditar:
			startActivity(new Intent(PerfilActivity.this, EditProfileActivity.class));
			break;
		}

		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.perfil, menu);
		return true;
	}
}

class GetTask extends AsyncTask<Integer, Void, Boolean>
{
        Context context;
		private ProgressDialog mDialog;
        
	    GetTask(Context context) {
	    	this.context = context;
	    }
	      
	    @Override
	    protected void onPreExecute() {
	        super.onPreExecute();

	         mDialog = new ProgressDialog(context);
	         mDialog.setMessage("A exportar...\nEsta accao pode demorar alguns segundos");
	         mDialog.show();
	    }


	    protected Boolean doInBackground(Integer... id) {
	    	return ExportFolio.createFolio(id[0], context);
	    }
	                
	    @Override
	    protected void onPostExecute(Boolean result) {
	        super.onPostExecute(result);
	        mDialog.dismiss();
	        
	        if(result)
				Toast.makeText(context, "Exportacao bem sucedida", Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(context, "Exportacao mal sucedida", Toast.LENGTH_SHORT).show();        
	    }
    }
