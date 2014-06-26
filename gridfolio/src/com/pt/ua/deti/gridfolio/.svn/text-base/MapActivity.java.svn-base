package com.pt.ua.deti.gridfolio;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pt.ua.deti.gridfolio.singleton.AppDataAllEvents;

@SuppressLint("NewApi")
public class MapActivity extends Activity {
	private LatLng frameworkSystemLocation = new LatLng(40.635552, -8.659136);
	private GoogleMap map;
	private int id;

	@SuppressWarnings("unused")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		bar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		
		Bundle bd = getIntent().getExtras();
		if(bd != null) {
			id = bd.getInt("ID_EVENTO");
		}
		else id = -1;
		
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		map.setMyLocationEnabled(true);
		for (int i = 0; i < AppDataAllEvents.getInstance().getNumb(); i++) {
			Marker frameworkSystem = map.addMarker(new MarkerOptions()
					.position(new LatLng(Double.parseDouble(AppDataAllEvents.getInstance().getLatitude(i)), Double.parseDouble(AppDataAllEvents.getInstance().getLongitude(i))))
					.title(AppDataAllEvents.getInstance().getNome(i))
					.snippet("Leva-me lá..."));
		}
		
		if(id < 0) {
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(frameworkSystemLocation, 15));
		}
		else
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(
					new LatLng(Double.parseDouble(AppDataAllEvents.getInstance().getLatitude(id)), 
							   Double.parseDouble(AppDataAllEvents.getInstance().getLatitude(id))), 15));
		
		map.animateCamera(CameraUpdateFactory.zoomTo(17), 2000, null);
		
		map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
			
			@Override
			public void onInfoWindowClick(Marker marker) {
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
					    Uri.parse("http://maps.google.com/maps?saddr=" + map.getMyLocation().getLatitude() + 
					    		"," + map.getMyLocation().getLongitude() + "&daddr=" + 
					    		String.valueOf(marker.getPosition().latitude) + "," + 
					    		String.valueOf(marker.getPosition().longitude)));
					    		
				startActivity(intent);
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
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

}