package com.pt.ua.deti.gridfolio;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;

import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint({ "NewApi", "CutPasteId" })
public class CreateProjectActivity extends Activity {

	// WebServices EventValues
	private static String Descriptions, disciplina, titulo, professores;
	private static Bitmap bitmap = null;
	private static ImageView mPicture;

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
		setContentView(R.layout.activity_create_project);

		mPicture = (ImageView) findViewById(R.id.picture_1);

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_project, menu);
		return true;
	}

	@Override
	public void onResume() {
		super.onResume(); // Always call the superclass method first
		if (bitmap != null)
			mPicture.setImageBitmap(bitmap);
	}

	@SuppressWarnings("unused")
	private void saveChanges() {
		EditText textTmp = (EditText) findViewById(R.id.editTextTitle);
		titulo = textTmp.getText().toString();
		textTmp = (EditText) findViewById(R.id.editTextDisciplina);
		disciplina = textTmp.getText().toString();
		textTmp = (EditText) findViewById(R.id.editTextTeachers);
		professores = textTmp.getText().toString();
		textTmp = (EditText) findViewById(R.id.editTextDescription);
		Descriptions = textTmp.getText().toString();
	}

	public void saveChanges(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(getResources().getString(R.string.criado_projecto));
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {

			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public class CreateProjectTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Void... params) {
			// Soap values
			final String SOAP_ACTION = "http://tempuri.org/InserirProjecto";
			final String METHOD_NAME = "InserirProjecto";
			final String NAMESPACE = "http://tempuri.org/";
			final String URL = "http://conquerua.meligaletiko.tk/WebServicesConquerUA.asmx?op=InserirProjecto";
			String result = null;

			try {

				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

				PropertyInfo pi = new PropertyInfo();
				pi.setName("name");
				pi.setValue(titulo);
				pi.setType(Integer.class);
				request.addProperty(pi);

				pi = new PropertyInfo();
				pi.setName("titulo");
				pi.setValue(titulo);
				pi.setType(Integer.class);
				request.addProperty(pi);

				pi = new PropertyInfo();
				pi.setName("disciplina");
				pi.setValue(disciplina);
				pi.setType(Integer.class);
				request.addProperty(pi);

				pi = new PropertyInfo();
				pi.setName("professores");
				pi.setValue(professores);
				pi.setType(Integer.class);
				request.addProperty(pi);

				pi = new PropertyInfo();
				pi.setName("descricao");
				pi.setValue(Descriptions);
				pi.setType(Integer.class);
				request.addProperty(pi);

				pi = new PropertyInfo();
				pi.setName("image");
				pi.setValue("vazio");
				pi.setType(Integer.class);
				request.addProperty(pi);

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
						SoapEnvelope.VER11);
				envelope.dotNet = true;

				envelope.setOutputSoapObject(request);

				AndroidHttpTransport androidHttpTransporta = new AndroidHttpTransport(
						URL);
				androidHttpTransporta.call(SOAP_ACTION, envelope);

				SoapObject response = (SoapObject) envelope.bodyIn;
				Log.i("result", "1");
				result = response.getProperty(0).toString();

				Log.i("result", result.toString());

			} catch (Exception e) {
				Log.i("result", e.toString());
				return false;
			}
			return null;
		}
	}

	public void pictureEvent(View view) {
		String[] addPhoto = new String[] { "Camera", "Gallery" };
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		// dialog.setTitle(getResources().getString(R.string.method));
		dialog.setTitle("Profile Picture");

		dialog.setItems(addPhoto, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {

				if (id == 0) {
					// with camera
					Intent takePicture = new Intent(
							MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(takePicture, 0);// zero can be
															// replced with any
															// action code
				}
				if (id == 1) {
					// from gallery
					Intent pickPhoto = new Intent(
							Intent.ACTION_PICK,
							android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					startActivityForResult(pickPhoto, 1);
				}
			}
		});

		dialog.setNeutralButton("cancel",
				new android.content.DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						dialog.dismiss();
					}
				});
		dialog.show();
	}

	protected void onActivityResult(int requestCode, int resultCode,
			Intent imageReturnedIntent) {
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
		switch (requestCode) {
		case 0:
			if (resultCode == RESULT_OK) {
				Bundle extras = imageReturnedIntent.getExtras();
				bitmap = (Bitmap) extras.get("data");
				mPicture.setImageBitmap(bitmap);
			}

			break;
		case 1:
			if (resultCode == RESULT_OK) {
				Uri selectedImage = imageReturnedIntent.getData();
				mPicture.setImageURI(selectedImage);
			}
			break;
		}
	}

}
