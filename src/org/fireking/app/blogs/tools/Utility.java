package org.fireking.app.blogs.tools;

import android.content.Context;

public class Utility {

	public static int dip2px(Context context, int value) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (value * scale + 0.5f);
	}
}
