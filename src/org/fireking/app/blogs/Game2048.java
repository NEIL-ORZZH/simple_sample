package org.fireking.app.blogs;

import org.fireking.app.blogs.game._2048.GameContainer;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class Game2048 extends Activity {

	GameContainer container;

	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_2048);

		LinearLayout games = (LinearLayout) this.findViewById(R.id.games);
		setGameView(games);

	}

	private void setGameView(LinearLayout games) {
		container = GameContainer.newInstance(this);
		DisplayMetrics metrices = new DisplayMetrics();
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
				metrices.widthPixels, metrices.widthPixels);
		games.addView(container.getView(), lp);
	};
}
