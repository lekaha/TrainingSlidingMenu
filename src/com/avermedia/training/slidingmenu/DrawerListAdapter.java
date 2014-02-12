package com.avermedia.training.slidingmenu;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * 
 * Customizing the list layout.
 * Let item of list to has icon, title, and subtitle.
 *
 * @param <T>
 */
public class DrawerListAdapter<T> extends ArrayAdapter<T> {
	protected Context context;
	protected List<T> items;
	protected LayoutInflater vi;

	public DrawerListAdapter(Context context, int resource, int textViewResourceId,
			List<T> objects) {
		super(context, resource, textViewResourceId, objects);
	}

	public DrawerListAdapter(Context context, int resource, int textViewResourceId,
			T[] objects) {
		super(context, resource, textViewResourceId, objects);
	}

	public DrawerListAdapter(Context context, int resource, int textViewResourceId) {
		super(context, resource, textViewResourceId);
	}

	public DrawerListAdapter(Context context, int resource, List<T> objects) {
		super(context, resource, objects);
		this.context = context;
		this.items = objects;
	}

	public DrawerListAdapter(Context context, int resource, T[] objects) {
		super(context, resource, objects);
	}

	public DrawerListAdapter(Context context, int resource) {
		super(context, resource);
	}
		 
	public DrawerListAdapter(Context context, List<T> items) {
		super(context, 0, items);
		this.context = context;
		this.items = items;
		this.vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//				getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	 
	 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		 
		final T t = items.get(position);
		if (null != t && t instanceof Item) {
			Item i = (Item) t;
			if(i.isSection()) {
				SectionItem si = (SectionItem)i;
				v = vi.inflate(R.layout.list_item_section, null);
		 
				v.setOnClickListener(null);
				v.setOnLongClickListener(null);
				v.setLongClickable(false);
		     
				final TextView sectionView = (TextView) v.findViewById(R.id.list_item_section_text);
				sectionView.setText(si.getTitle());
		     
			} else {
				EntryItem ei = (EntryItem)i;
				v = vi.inflate(R.layout.list_item_entry, null);
				final TextView title = (TextView)v.findViewById(R.id.list_item_entry_title);
				final TextView subtitle = (TextView)v.findViewById(R.id.list_item_entry_summary);
		     
		     
				if(null != title && null != ei.title)
					title.setText(ei.title);
				if(null != subtitle && null != ei.subtitle)
					subtitle.setText(ei.subtitle);
			}
			return v;
		}
		
		return super.getView(position, convertView, parent);
	}

}

interface Item {
	public boolean isSection();
}

class SectionItem implements Item{
	 
	private final String title;
	  
	public SectionItem(String title) {
		this.title = title;
	}
	  
	public String getTitle(){
		return title;
	}
	  
	@Override
	public boolean isSection() {
		return true;
	}
	
	@Override
	public String toString(){
		return title;
	}
	 
}

class EntryItem implements Item{
	 
	public final String title;
	public final String subtitle;
	  
	public EntryItem(String title) {
		this.title = title;
		this.subtitle = null;
	}
	
	public EntryItem(String title, String subtitle) {
		this.title = title;
		this.subtitle = subtitle;
	}
	  
	public String getTitle(){
		return title;
	}
	  
	@Override
	public boolean isSection() {
		return false;
	}
	
	@Override
	public String toString(){
		return title;
	}
	 
}
