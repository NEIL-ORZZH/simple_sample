package org.fireking.app.blogs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void slidingmenu01(View view) {
		router(SlidingMenu001.class);
	}

	public void slidingmenu02(View view) {
		router(SlidingMenu002.class);
	}

	public void slidingmenu03(View view) {
		router(SlidingMenu003.class);
	}

	public void slidingmenu04(View view) {
		router(SlidingMenu004.class);
	}

	public void slidingmenu05(View view) {
		router(SlidingMenu005.class);
	}

	public void lekan_video(View view) {
		router(LeKanVideo.class);
	}

	public void gameview1(View view) {
		router(Game2048.class);
	}

	private void router(Class clazz) {
		Intent intent = new Intent(MainActivity.this, clazz);
		startActivity(intent);
	}
}
