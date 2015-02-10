package org.fireking.app.blogs.cus_view;

import org.fireking.app.blogs.R;
import org.fireking.app.blogs.cus_view.view.DotView;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;

public class View2Aty extends Activity {

	DotView dot1, dot2, dot3;

	int dot1X = 0;
	int dot2X = 0;
	int dot3X = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view2_aty);

		dot1 = (DotView) this.findViewById(R.id.dot1);
		dot2 = (DotView) this.findViewById(R.id.dot2);
		dot3 = (DotView) this.findViewById(R.id.dot3);

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		dot1X = (int) dot1.getX();
		dot2X = (int) dot2.getX();
		dot3X = (int) dot3.getX();
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			com.nineoldandroids.animation.ObjectAnimator anim = com.nineoldandroids.animation.ObjectAnimator
					.ofFloat(dot1, "X", dot2X, dot3X).setDuration(500);
			anim.setRepeatMode(-1);
			anim.start();
		}
	}
}
