package com.pt.ua.deti.gridfolio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import com.pt.ua.deti.gridfolio.singleton.AppDataAllEvents;
import com.pt.ua.deti.gridfolio.threadsEvents.AllEventsService;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import android.widget.TextView;

import android.widget.Toast;


@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
@SuppressLint("CutPasteId")
public class ShowMyEventsActivity extends Activity {
	// Top Date bar necessary values
	private TextView mTextView;
	private ImageView imgViewPrevious;
	private int currentMonth, currentYear, displayMonth, displayYear;
	private String formattedDate;
	private String[] months = { "auxiliar", "JANEIRO", "FEVEREIRO", "MARÇO",
			"ABRIL", "MAIO", "JUNHO", "JULHO", "AGOSTO", "SETEMBRO", "OUTUBRO",
			"NOVEMBRO", "DEZEMBRO" };
	@SuppressLint("SimpleDateFormat")
	SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

	private GridView gv;
	private ListView mainListView;
	private ArrayAdapter<String> listAdapter;
	private LinearLayout slidingPanel;
	private boolean isExpanded;
	private TextView date;
	private int panelWidth;
	private GestureDetector gestureDetector = null;;
	private DisplayMetrics metrics;
	FrameLayout.LayoutParams slidingPanelParameters;
	FrameLayout.LayoutParams menuPanelParameters;
	LinearLayout.LayoutParams headerPanelParameters;
	LinearLayout.LayoutParams listViewParameters;
	AlertDialog.Builder dialog;
	public ProgressDialog pd = null;

	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_my_events);
		
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		bar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		
		this.pd = ProgressDialog.show(this, "", "A carregar os meus eventos...");
		
		AllEventsService events = new AllEventsService(this);
		try {
			events.execute().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		date = (TextView) findViewById(R.id.dateSwitcher);
		date.setText("Maio 2013");

		gv = (GridView) findViewById(R.id.gridViewMyEvents);
		if(AppDataAllEvents.getInstance().getDescricao(1) == null) {Log.i("ERROR", "NULL");
		}
		gv.setAdapter(new MyAdapter(this,5));
		dialog = new AlertDialog.Builder(this);

		gv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
				final int aux = pos;
				dialog.setTitle(AppDataAllEvents.getInstance().getNome(pos));
				
				dialog.setPositiveButton(R.string.ver, new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   Intent goEvent = new Intent(getApplicationContext(), VerEventoFeitoActivity.class);
							goEvent.putExtra("ID_EVENTO", aux);
							startActivity(goEvent);
			           }
			       });
				dialog.setNegativeButton(R.string.validation, new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   Toast.makeText(getApplicationContext(), R.string.event_validation, Toast.LENGTH_LONG).show();
			           }
			       });
			
			AlertDialog xpto = dialog.create();
			xpto.show();
				

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

		/*
		 * gets the value of current date (makes no sense display event passed
		 * on the mainScreen)
		 */

		String[] sArray = new String[3];
		formattedDate = df.format(Calendar.getInstance().getTime());
		sArray = formattedDate.split("-");
		currentMonth = Integer.valueOf(sArray[1]);
		displayMonth = currentMonth;
		currentYear = Integer.valueOf(sArray[2]);
		displayYear = currentYear;
		Log.d("Date", formattedDate);

		imgViewPrevious = (ImageView) findViewById(R.id.previous);
		imgViewPrevious.setVisibility(View.INVISIBLE);
		mTextView = (TextView) findViewById(R.id.dateSwitcher);
		// mSwitcher = (TextSwitcher) findViewById(R.id.dateSwitcher);
		// mSwitcher.setFactory(this);

		@SuppressWarnings("unused")
		Animation in = AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in);
		@SuppressWarnings("unused")
		Animation out = AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out);

		mTextView.setText(months[currentMonth] + " " + currentYear);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.eventsAdd:
			LayoutInflater factory = LayoutInflater.from(this);
			final View EventView = factory.inflate(R.layout.dialog_events, null);

			Builder dialog = new AlertDialog.Builder(this);
			
			dialog.setTitle("Eventos");

			dialog.setCancelable(true);

			mainListView = (ListView) EventView.findViewById(R.id.listView1);

			String[] planets = new String[] { "UA - RobotFight", "Google I/O",
					"Air Week", "Programação Java" };

			ArrayList<String> planetList = new ArrayList<String>();
			for (int i = 0; i < planets.length; i++)
				planetList.add(planets[i]);

			listAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_listview, planetList);

			mainListView.setAdapter(listAdapter);

			mainListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View view, int position, long arg) {
					Intent goEvent = new Intent(getApplicationContext(), EventsActivity.class);
					goEvent.putExtra("ID_PROJETO", position);
					startActivity(goEvent);
				}
			});

			dialog.setView(EventView).setNegativeButton(R.string.cancel, null).create();
			dialog.show();
			break;
		case R.id.projetoAdd:
			startActivity(new Intent(getApplicationContext(), CreateProjectActivity.class));
			break;
		}
		return true;
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_my_events, menu);
		MenuItem menuItem = menu.findItem(R.id.menu_search);

		menuItem.setOnActionExpandListener(new OnActionExpandListener() {
			@Override
			public boolean onMenuItemActionCollapse(MenuItem item) {
				// Do something when collapsed
				return true; // Return true to collapse action view
			}

			@Override
			public boolean onMenuItemActionExpand(MenuItem item) {
				// Do something when expanded
				return true; // Return true to expand action view
			}
		});
		return true;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	public void nextMonth(View view) {
		String value = null;
		displayMonth++;
		imgViewPrevious.setVisibility(View.VISIBLE);
		if (displayMonth > 12) {
			displayYear += 1;
			displayMonth = 1;
			value = (months[displayMonth] + " " + displayYear);
		} else {
			value = (months[displayMonth] + " " + displayYear);
		}
		mTextView.setText(value);

	}

	public void previousMonth(View view) {
		String value = null;
		displayMonth--;
		if (displayMonth < 1) {
			displayYear--;
			displayMonth = 12;
			value = (months[displayMonth] + " " + displayYear);
			if (displayYear < currentYear) {
				displayMonth = currentMonth;
				displayYear = currentYear;
				imgViewPrevious.setVisibility(View.INVISIBLE);
				value = (months[displayMonth] + " " + displayYear);
			}
		} else {
			value = (months[displayMonth] + " " + displayYear);
			if (displayYear == currentYear) {
				if (displayMonth <= currentMonth) {
					displayMonth = currentMonth;
					imgViewPrevious.setVisibility(View.INVISIBLE);
				}
				value = (months[displayMonth] + " " + displayYear);
			}
		}

		mTextView.setText(value);
	}

}
