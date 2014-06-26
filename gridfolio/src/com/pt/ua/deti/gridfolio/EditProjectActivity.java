package com.pt.ua.deti.gridfolio;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.pt.ua.deti.gridfolio.singleton.AppDataProject;

@SuppressLint({ "CutPasteId", "InlinedApi" })
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class EditProjectActivity extends Activity {

	// ProblemVariables

	// WebServices EventValues

	private int IDProjecto, flagStat;
	private static String Descriptions, disciplina, titulo, professores;

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
	PagerContainer mContainer;
	ViewPager viewPager;
	EditProjectAdapter adapter;
	GestureDetector tapGestureDetector;
	private static final int CAMERA_REQUEST = 1888;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_project);
		
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		bar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

		Bundle bd = getIntent().getExtras();
		
		EditText titulo = (EditText)findViewById(R.id.editTextTitle);
		EditText disciplina = (EditText)findViewById(R.id.editTextDisciplina);
		EditText professores = (EditText)findViewById(R.id.editTextTeachers);
		EditText descricao = (EditText)findViewById(R.id.editTextDescription);
		
		titulo.setText(AppDataProject.getInstance().gettitulo(bd.getInt("ID")));
		disciplina.setText(AppDataProject.getInstance().getDisciplina(bd.getInt("ID")));
		professores.setText(AppDataProject.getInstance().getProfessores(bd.getInt("ID")));
		descricao.setText(AppDataProject.getInstance().getDescricao(bd.getInt("ID")));
		
		mContainer = (PagerContainer)findViewById(R.id.pager_container);
		
		viewPager = mContainer.getViewPager();
	    adapter = new EditProjectAdapter(this);
	    viewPager.setAdapter(adapter);
	    viewPager.setOffscreenPageLimit(adapter.getCount());
		viewPager.setPageMargin(10);
		   
		//If hardware acceleration is enabled, you should also remove
		// clipping on the pager for its children.
		viewPager.setClipChildren(false);
		
		tapGestureDetector = new GestureDetector(this, new TapGestureListener());
		
		viewPager.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				tapGestureDetector.onTouchEvent(event);		
				return false;
			}
		});
		
		findViewById(R.id.buttonSave).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				GetProjectTask projectTask = new GetProjectTask();
//				try {
//					projectTask.execute().get();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (ExecutionException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				startActivity(new Intent(getApplicationContext(), ShowMyProjectsActivity.class));
				
			}
		});
		
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
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {  
			//Adiciona no MAP das imagens, que ainda nao esta implementado e faz reload  as imagens
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_event, menu);
		return true;
	}

	public class GetProjectTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// Soap values
			final String SOAP_ACTION = "http://tempuri.org/getProjectsID";
			final String METHOD_NAME = "getProjectsID";
			final String NAMESPACE = "http://tempuri.org/";
			final String URL = "http://conquerua.meligaletiko.tk/WebServicesConquerUA.asmx?op=getProjectsID";
			String result = null;

			try {

				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

				PropertyInfo pi = new PropertyInfo();
				pi.setName("id");
				pi.setValue(IDProjecto);
				pi.setType(Integer.class);
				request.addProperty(pi);

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
						SoapEnvelope.VER11);
				envelope.dotNet = true;

				envelope.setOutputSoapObject(request);

				AndroidHttpTransport androidHttpTransporta = new AndroidHttpTransport(
						URL);
				androidHttpTransporta.call(SOAP_ACTION, envelope);

				SoapObject response = (SoapObject) envelope.getResponse();
				SoapObject pii = (SoapObject) response.getProperty(0);

				result = response.getProperty(0).toString();
				Log.i("result", result.toString());

				IDProjecto = Integer.parseInt(pii.getProperty(0).toString());
				Log.i("IDProjecto", "" + IDProjecto);
				flagStat = Integer.parseInt(pii.getProperty(1).toString());
				Log.i("flagStat", "" + flagStat);
				Descriptions = pii.getProperty(2).toString();
				Log.i("Descriptions", Descriptions);
				disciplina = pii.getProperty(3).toString();
				Log.i("disciplina", disciplina);
				titulo = pii.getProperty(4).toString();
				Log.i("titulo", titulo);
				professores = pii.getProperty(5).toString();
				Log.i("professores", professores);

			} catch (Exception e) {
				Log.i("result", e.toString());
			}
			return null;
		}
	}
	
	class TapGestureListener extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
    		if(viewPager.getCurrentItem() == adapter.getCount() - 1) {
    			startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), CAMERA_REQUEST);
    		}
			return true;
        }
	}
}
