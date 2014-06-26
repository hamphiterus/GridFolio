package com.pt.ua.deti.gridfolio;

import com.pt.ua.deti.gridfolio.EditProjectActivity.TapGestureListener;
import com.pt.ua.deti.gridfolio.singleton.AppDataUser;

import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class EditEventActivity extends Activity {
	
	PagerContainer mContainer;
	ViewPager viewPager;
	EditProjectAdapter adapter;
	GestureDetector tapGestureDetector;
	private static final int CAMERA_REQUEST = 1888;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_event);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		
		Bundle bd = getIntent().getExtras();
		
		EditText notas = (EditText)findViewById(R.id.editNotes);
		
		notas.setText(AppDataUser.getInstance().getName());
		
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.edit_event, menu);
		return true;
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {  
			//Adiciona no MAP das imagens, que ainda nao esta implementado e faz reload  as imagens
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
