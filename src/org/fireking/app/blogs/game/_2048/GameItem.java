package org.fireking.app.blogs.game._2048;

import org.fireking.app.blogs.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class GameItem {

	public Context mContext;

	private TextView rootView;

	private int tagText;

	public GameItem(Context context) {
		this.mContext = context;
		TextView view = rootView = this.createView();
		this.viewCreated(view);
	}

	public static GameItem newInstance(Context context) {
		return new GameItem(context);
	}

	private TextView createView() {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		return (TextView) inflater.inflate(R.layout.g_2048_item, null);
	}

	private void viewCreated(TextView view) {

	}

	public void setText(int tagText) {
		this.tagText = tagText;
	}

	public int getText() {
		return tagText;
	}

	public View getView() {
		return rootView;
	}
}
