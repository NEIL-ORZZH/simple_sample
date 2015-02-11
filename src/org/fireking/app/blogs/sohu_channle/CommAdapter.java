package org.fireking.app.blogs.sohu_channle;

import java.util.LinkedList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CommAdapter<T> extends BaseAdapter {

	private List<T> list = new LinkedList<T>();

	protected void add(List<T> collection) {
		list.addAll(collection);
		notifyDataSetChanged();
	}

	protected void clear() {
		if (list != null) {
			list.clear();
			notifyDataSetChanged();
		}
	}

	protected void replace(List<T> mlist) {
		this.list = mlist;
		notifyDataSetChanged();
	}

	protected List<T> list() {
		return list;
	}

	@Override
	public int getCount() {
		return list == null || list.size() == 0 ? 0 : list.size();
	}

	@Override
	public Object getItem(int position) {
		return list == null || list.size() == 0 ? null : list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getCinvertView(position, convertView, parent);
	}

	public abstract View getCinvertView(int position, View convertView,
			ViewGroup parent);
}
