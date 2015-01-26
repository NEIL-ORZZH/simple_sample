package org.fireking.app.blogs.game._2048;

import org.fireking.app.blogs.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class GameContainer {

	private Context mContext;

	private static GameContainer instance = null;

	private LinearLayout rootView;

	// 行数、列数
	private int column = 4;

	private GameContainer(Context context) {
		this.mContext = context;
		LinearLayout view = rootView = this.createView();
		this.viewCreated(view);
	}

	public static GameContainer newInstance(Context context) {
		if (instance == null)
			instance = new GameContainer(context);
		return instance;
	}

	public LinearLayout getView() {
		return rootView;
	}

	public void reflesh() {

	}

	public LinearLayout createView() {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		return (LinearLayout) inflater.inflate(R.layout.g_2048_container, null);
	}

	public void viewCreated(LinearLayout view) {

		for (int i = 0; i < column; i++) {
			for (int j = 0; j < column; j++) {
				
			}
		}
	}

	public void render() {

	}
}
