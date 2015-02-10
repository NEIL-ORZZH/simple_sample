package org.fireking.app.blogs.property_animation;

import org.fireking.app.blogs.R;
import org.fireking.app.blogs.inject.framework.KimiraInject;
import org.fireking.app.blogs.inject.framework.anotation.InjectView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class PropertyAnimationTest extends Activity {

	@InjectView(R.id.test_objectAnimation)
	private TextView testObjAnimation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.propert_animation);
		KimiraInject.init(this);
		testObjAnimation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
			}
		});

	}
}
