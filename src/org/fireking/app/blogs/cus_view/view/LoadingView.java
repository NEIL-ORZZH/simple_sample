package org.fireking.app.blogs.cus_view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class LoadingView extends LinearLayout {

	public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		setOrientation(HORIZONTAL);
	}

	public LoadingView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LoadingView(Context context) {
		this(context, null);
	}

}
