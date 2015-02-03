package org.fireking.app.blogs.menu;

import org.fireking.app.blogs.R;
import org.fireking.app.blogs.R.id;
import org.fireking.app.blogs.R.layout;
import org.fireking.app.blogs.view.slidingmenu.SlidingMenu_001;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class SlidingMenu001 extends Activity {
	SlidingMenu_001 slidingMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slidingmenu_001);
		slidingMenu = (SlidingMenu_001) this.findViewById(R.id.slidingmenu);
	}

	public void toggleMenu(View view) {
		slidingMenu.toggle();
	}
}
