package org.fireking.app.blogs.inject;

import org.fireking.app.blogs.R;
import org.fireking.app.blogs.inject.framework.KimiraInject;
import org.fireking.app.blogs.inject.framework.anotation.ContextView;
import org.fireking.app.blogs.inject.framework.anotation.InjectView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@ContextView(value = R.layout.inject_sample)
public class InjectFramework extends Activity {

	@InjectView(R.id.thanks)
	private TextView thanks;

	@InjectView(R.id.test)
	private Button test;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		KimiraInject.init(this);

		thanks.setClickable(true);
		thanks.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(InjectFramework.this, "click thank,,,",
						Toast.LENGTH_SHORT).show();
			}
		});

		test.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(InjectFramework.this, "click test....",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}
