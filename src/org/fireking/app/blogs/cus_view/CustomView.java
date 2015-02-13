package org.fireking.app.blogs.cus_view;

import org.fireking.app.blogs.R;
import org.fireking.app.blogs.inject.framework.KimiraInject;
import org.fireking.app.blogs.inject.framework.anotation.InjectView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class CustomView extends Activity {

	@InjectView(R.id.mView2)
	private TextView mView2;

	@InjectView(R.id.mView1)
	private TextView mView1;

	@InjectView(R.id.mView3)
	private TextView mView3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cus_view);

		KimiraInject.init(this);

		mView1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				router(View1Aty.class);
			}
		});

		mView2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				router(View2Aty.class);
			}
		});

		mView3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				router(View3Aty.class);
			}
		});
	}

	private void router(Class clazz) {
		Intent intent = new Intent(this, clazz);
		startActivity(intent);
	}
}
