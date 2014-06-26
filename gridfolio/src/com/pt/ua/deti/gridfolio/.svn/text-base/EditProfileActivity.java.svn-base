package com.pt.ua.deti.gridfolio;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
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

import com.pt.ua.deti.gridfolio.singleton.AppDataUser;

@SuppressLint("CutPasteId")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class EditProfileActivity extends Activity {
	
	private ImageView imageView;
	private static final int CAMERA_REQUEST = 1888;
	private static final int SELECT_PHOTO = 100;
	private String selectedImagePath;
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
		setContentView(R.layout.activity_edit_profile);
		
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		bar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		
		EditText curso = (EditText) findViewById(R.id.curso);
		EditText ano = (EditText) findViewById(R.id.ano);
		EditText data = (EditText) findViewById(R.id.data);
		EditText name = (EditText) findViewById(R.id.namePerson);
		
		ano.setText(AppDataUser.getInstance().getAno());
		data.setText(AppDataUser.getInstance().getData());
		curso.setText(AppDataUser.getInstance().getCourse());
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
	
	public void pictureEvent(View view) {
		String[] addPhoto = new String[] { "Camera", "Gallery" };
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("Profile Picture");
		dialog.setCancelable(true);

		dialog.setItems(addPhoto, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {

				if (id == 0) {
					// with camera
					startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), 0);
				}
				if (id == 1) {
					// from gallery
					Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					startActivityForResult(pickPhoto, 1);
				}
			}
		});

		dialog.setNeutralButton("cancel", null);
		dialog.show();
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		imageView = (ImageView)findViewById(R.id.image_event);
		if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
			Bitmap photo = (Bitmap) data.getExtras().get("data");
			imageView.setImageBitmap(photo);
		}
		if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK) {
			Uri selectedImageUri = data.getData();
			selectedImagePath = getPath(selectedImageUri);
			imageView.setImageBitmap(BitmapFactory.decodeFile(selectedImagePath));
		}
	}
	
	@SuppressWarnings("deprecation")
	public String getPath(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.edit_profile, menu);
		return true;
	}

}
