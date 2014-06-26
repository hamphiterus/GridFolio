package com.pt.ua.deti.gridfolio;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.pt.ua.deti.gridfolio.singleton.AppDataAllEvents;
import com.pt.ua.deti.gridfolio.singleton.AppDataEventsAmanha;
import com.pt.ua.deti.gridfolio.singleton.AppDataEventsBreve;
import com.pt.ua.deti.gridfolio.singleton.AppDataEventsHoje;
import com.pt.ua.deti.gridfolio.singleton.AppDataProject;

public class MyAdapter extends BaseAdapter {
	private List<Item> items = new ArrayList<Item>();
	private LayoutInflater inflater;
	private int id;

	public MyAdapter(Context context, int id) {
		inflater = LayoutInflater.from(context);
		this.id = id;
		if (id == 1) {
			for (int i = 0; i < AppDataEventsHoje.getInstance().getNumbEvents(); i++) {
				items.add(new Item(AppDataEventsHoje.getInstance().getNome(i)
						.toString().toString(), R.drawable.sample_0));
			}
		}
		// amanha
		if (id == 2) {
			for (int i = 0; i < AppDataEventsAmanha.getInstance()
					.getNumbEvents(); i++) {
				items.add(new Item(AppDataEventsAmanha.getInstance().getNome(i)
						.toString().toString(), R.drawable.sample_0));
			}
		}
		// em breve
		if (id == 3) {
			for (int i = 0; i < AppDataEventsBreve.getInstance()
					.getNumbEvents(); i++) {
				items.add(new Item(AppDataEventsBreve.getInstance().getNome(i)
						.toString().toString(), R.drawable.sample_0));
			}
		}
		if (id == 4) {

			for (int i = 0; i < AppDataProject.getInstance().getNumbProjetos(); i++) {
					
				items.add(new Item(AppDataProject.getInstance().gettitulo(i)
						.toString().toString(), R.drawable.sample_0));
			}

		}
		// todos
		if (id == 5) {
			for (int i = 0; i < AppDataAllEvents.getInstance().getNumb(); i++) {

				items.add(new Item(AppDataAllEvents.getInstance().getNome(i)
						.toString(), R.drawable.sample_0));
			}
		}
		// decodeWebService();

	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int i) {
		return items.get(i);
	}

	@Override
	public long getItemId(int i) {
		return items.get(i).drawableId;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		View v = view;
		ImageView picture;
		TextView name;

		if (v == null) {
			v = inflater.inflate(R.layout.mygrid_layout, viewGroup, false);
			v.setTag(R.id.image, v.findViewById(R.id.image));
			v.setTag(R.id.text, v.findViewById(R.id.text));
		}
		picture = (ImageView) v.getTag(R.id.image);
		name = (TextView) v.getTag(R.id.text);
		Item item = (Item) getItem(i);
		//picture.setImageResource(item.drawableId);

		// // todos
		if (AppDataAllEvents.getInstance().getRefGroup(i) == 1 && id == 5) {
			picture.setBackgroundColor(Color.rgb(245, 187, 23));
		}
		if (AppDataAllEvents.getInstance().getRefGroup(i) == 2 && id == 5) {
			picture.setBackgroundColor(Color.rgb(182, 151, 195));
		}
		if (AppDataAllEvents.getInstance().getRefGroup(i) == 3 && id == 5) {
			picture.setBackgroundColor(Color.rgb(234, 122, 55));
		}
		if (AppDataAllEvents.getInstance().getRefGroup(i) == 4 && id == 5) {
			picture.setBackgroundColor(Color.rgb(222, 78, 54));
		}

		if (id == 4) {
//			byte[] encodeByte = Base64.decode(AppDataProject
//					.getInstance().getImageProject(i).toString(),
//					Base64.DEFAULT);
//			Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte,
//					0, encodeByte.length);
//			picture.setImageBitmap(bitmap);
			picture.setBackgroundColor(Color.rgb(222, 78, 54));
			
		}
		if (id == 1 || id == 2 || id == 3) {
			if (id == 3) {
				if (AppDataEventsBreve.getInstance().getRefGroup(i) == 1
						&& id == 3) {
					picture.setBackgroundColor(Color.rgb(245, 187, 23));
				}
				if (AppDataEventsBreve.getInstance().getRefGroup(i) == 2
						&& id == 3) {
					picture.setBackgroundColor(Color.rgb(182, 151, 195));
				}
				if (AppDataEventsBreve.getInstance().getRefGroup(i) == 3
						&& id == 3) {
					picture.setBackgroundColor(Color.rgb(234, 122, 55));
				}
				if (AppDataEventsBreve.getInstance().getRefGroup(i) == 4
						&& id == 3) {
					picture.setBackgroundColor(Color.rgb(222, 78, 54));
				}
			}

			// AMANHA
			if (id == 2) {
				if (AppDataEventsAmanha.getInstance().getRefGroup(i) == 1
						&& id == 2) {
					picture.setBackgroundColor(Color.rgb(245, 187, 23));
				}
				if (AppDataEventsAmanha.getInstance().getRefGroup(i) == 2
						&& id == 2) {
					picture.setBackgroundColor(Color.rgb(182, 151, 195));
				}
				if (AppDataEventsAmanha.getInstance().getRefGroup(i) == 3
						&& id == 2) {
					picture.setBackgroundColor(Color.rgb(234, 122, 55));
				}
				if (AppDataEventsAmanha.getInstance().getRefGroup(i) == 4
						&& id == 2) {
					picture.setBackgroundColor(Color.rgb(222, 78, 54));
				}
			}
			if (id == 1) {
				if (AppDataEventsHoje.getInstance().getRefGroup(i) == 1
						&& id == 1) {
					picture.setBackgroundColor(Color.rgb(245, 187, 23));
				}
				if (AppDataEventsHoje.getInstance().getRefGroup(i) == 2
						&& id == 1) {
					picture.setBackgroundColor(Color.rgb(182, 151, 195));
				}
				if (AppDataEventsHoje.getInstance().getRefGroup(i) == 3
						&& id == 1) {
					picture.setBackgroundColor(Color.rgb(234, 122, 55));
				}
				if (AppDataEventsHoje.getInstance().getRefGroup(i) == 4
						&& id == 1) {
					picture.setBackgroundColor(Color.rgb(222, 78, 54));
				}
			}
		}
		name.setText(item.name);

		return v;
	}

	private class Item {
		final String name;
		final int drawableId;
		

		Item(String name, int drawableId) {
			this.name = name;
			this.drawableId = drawableId;
		}
		

	}
}