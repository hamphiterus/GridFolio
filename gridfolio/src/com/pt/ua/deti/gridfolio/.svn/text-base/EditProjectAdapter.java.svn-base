package com.pt.ua.deti.gridfolio;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class EditProjectAdapter extends PagerAdapter {
	Context context;
	Integer pics[] = { R.drawable.saleiro_1, R.drawable.saleiro_2, R.drawable.saleiro_3, R.drawable.adicionarfoto };

	EditProjectAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return pics.length;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((ImageView) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView imageView = new ImageView(context);
		
		int padding = context.getResources().getDimensionPixelSize(R.dimen.padding_medium);
		
		imageView.setPadding(0, padding, 0, padding);
		imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		imageView.setImageResource(pics[position]);
		
		container.addView(imageView);
		return imageView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((ImageView) object);
	}
}
