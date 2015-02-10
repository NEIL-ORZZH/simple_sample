package org.fireking.app.blogs.service;

import org.fireking.app.blogs.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class UseService extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.use_service);

		TextView start = (TextView) this.findViewById(R.id.start);

		TextView stop = (TextView) this.findViewById(R.id.stop);

		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
	}
}
