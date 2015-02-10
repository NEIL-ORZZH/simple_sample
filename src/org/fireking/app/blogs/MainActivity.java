package org.fireking.app.blogs;

import org.fireking.app.blogs.cus_view.CustomView;
import org.fireking.app.blogs.g2048.Game2048;
import org.fireking.app.blogs.inject.InjectFramework;
import org.fireking.app.blogs.menu.SlidingMenu001;
import org.fireking.app.blogs.menu.SlidingMenu002;
import org.fireking.app.blogs.menu.SlidingMenu003;
import org.fireking.app.blogs.menu.SlidingMenu004;
import org.fireking.app.blogs.menu.SlidingMenu005;
import org.fireking.app.blogs.pgb_hy.HyProgressBar;
import org.fireking.app.blogs.property_animation.PropertyAnimationTest;
import org.fireking.app.blogs.service.UseService;
import org.fireking.app.blogs.video.LeKanVideo;

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

	public void game2048(View view) {
		router(Game2048.class);
	}

	public void injectframework(View view) {
		router(InjectFramework.class);
	}

	public void progressbar_hy(View view) {
		router(HyProgressBar.class);
	}

	public void useService(View view) {
		router(UseService.class);
	}

	public void propertyAnimation(View view) {
		router(PropertyAnimationTest.class);
	}

	public void customView(View view) {
		router(CustomView.class);
	}

	private void router(Class clazz) {
		Intent intent = new Intent(MainActivity.this, clazz);
		startActivity(intent);
	}
}
