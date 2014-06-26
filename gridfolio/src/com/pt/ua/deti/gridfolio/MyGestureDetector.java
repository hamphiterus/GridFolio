package com.pt.ua.deti.gridfolio;

import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

public class MyGestureDetector extends SimpleOnGestureListener {
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private int flag = 0;
	private LinearLayout slidingPanel;
	private boolean isExpanded;
	private int panelWidth;

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {


		if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {

		}

		// right to left swipe
		if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
				&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
			// startActivity(intent);
			if (flag == 1) {
				flag = 0;
				isExpanded = false;

				// Collapse
				new CollapseAnimation(slidingPanel, panelWidth,
						TranslateAnimation.RELATIVE_TO_SELF, 0.75f,
						TranslateAnimation.RELATIVE_TO_SELF, 0.0f, 0, 0.0f, 0,
						0.0f);
			}

			// right to left swipe
		} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
				&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
			// startActivity(intent);
			if (flag == 0) {
				flag = 1;
				if (!isExpanded) {
					isExpanded = true;

					// Expand
					new ExpandAnimation(slidingPanel, panelWidth,
							Animation.RELATIVE_TO_SELF, 0.0f,
							Animation.RELATIVE_TO_SELF, 0.75f, 0, 0.0f, 0, 0.0f);
				}
			}

		}

		return false;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return true;
	}
}
