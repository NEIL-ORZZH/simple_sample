package org.fireking.app.blogs.shortcut;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.fireking.app.blogs.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

public class TestShortCut extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_shortcut);

		MyAsyncTast tast = new MyAsyncTast();
		tast.execute("http://d.hiphotos.baidu.com/image/h%3D200/sign=6b39ea62acc3793162688129dbc5b784/09fa513d269759ee2ea448afb1fb43166c22dfd9.jpg");
	}

	private void addShortCut(String nameString, String action, Bitmap bp) {
		Intent intent = new Intent(
				"com.android.launcher.action.INSTALL_SHORTCUT");
		// 设置标题
		intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, nameString);
		bp = bp.createScaledBitmap(bp, 72, 72, true);
		intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, bp);

		// 设置不可重复添加
		// Launcher.EXTRA_SHORTCUT_DUPLICATE
		intent.putExtra("duplicate", false);

		// 设置启动操作
		Intent actionIntent = new Intent(this, DyChannel.class);
		actionIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// 设置为启动页
		actionIntent.setAction("android.intent.action.MAIN");

		// 设置事件
		intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, actionIntent);

		sendBroadcast(intent);
	}

	class MyAsyncTast extends AsyncTask<String, Integer, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			String uri = params[0];
			try {
				return doGetImage(uri);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			addShortCut("µçÓ°ÆµµÀ", null, result);
		}

		private Bitmap doGetImage(String uri) throws Exception {
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.connect();
			InputStream is = connection.getInputStream();
			return BitmapFactory.decodeStream(is);
		}
	}
}
