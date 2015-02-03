package org.fireking.app.blogs.menu;

import org.fireking.app.blogs.R;
import org.fireking.app.blogs.R.id;
import org.fireking.app.blogs.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.nineoldandroids.view.ViewHelper;

public class SlidingMenu005 extends Activity {

	private DrawerLayout mDrawerLatout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_slidingmenu_005);

		initView();
		initEvent();
	}

	public void OpenRightMenu(View view) {
		mDrawerLatout.openDrawer(Gravity.RIGHT);
		mDrawerLatout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
				Gravity.RIGHT);
	}

	private void initEvent() {
		mDrawerLatout.setDrawerListener(new DrawerListener() {

			@Override
			public void onDrawerStateChanged(int arg0) {

			}

			@Override
			public void onDrawerSlide(View drawView, float slideoffset) {
				/**
				 * slideoffset-从0-1，在滑动的时候动态改变<br />
				 * DrawerLayout 默认第一个为主界面内容Content，第二个为菜单
				 */
				System.out.println("Item->" + slideoffset);
				// 拿到主布局
				ViewGroup mContent = (ViewGroup) mDrawerLatout.getChildAt(0);
				// 拿到滑动菜单
				View mMene = drawView;
				// 这里计算菜单偏移量 1-- 0
				float scale = 1 - slideoffset;
				// 右边菜单偏移量为 1 -- 0.8
				float rightScale = 0.8f + scale * 0.2f;
				// 判断菜单方位
				if ("LEFT".equals(drawView.getTag())) {
					// 0.7 - 1
					float leftScale = 1f - 0.3f * scale;
					ViewHelper.setScaleX(drawView, leftScale);
					ViewHelper.setScaleY(drawView, leftScale);
					ViewHelper.setAlpha(drawView, 0.6f + 0.4f * (1 - scale));
					ViewHelper.setTranslationX(mContent,
							mMene.getMeasuredWidth() * (1 - scale));
					ViewHelper.setPivotX(mContent, 0);
					ViewHelper.setPivotY(mContent,
							mContent.getMeasuredHeight() / 2);
					mContent.invalidate();
					ViewHelper.setScaleX(mContent, rightScale);
					ViewHelper.setScaleY(mContent, rightScale);
				} else {
					ViewHelper.setTranslationX(mContent,
							-mMene.getMeasuredWidth() * slideoffset);
					ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
					ViewHelper.setPivotX(mContent,
							mContent.getMeasuredHeight() / 2);
					mContent.invalidate();
					ViewHelper.setScaleX(mContent, rightScale);
					ViewHelper.setScaleY(mContent, rightScale);
				}
			}

			@Override
			public void onDrawerOpened(View arg0) {

			}

			@Override
			public void onDrawerClosed(View arg0) {
				mDrawerLatout.setDrawerLockMode(
						DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
			}
		});
	}

	private void initView() {
		mDrawerLatout = (DrawerLayout) this.findViewById(R.id.id_drawerLayout);
		mDrawerLatout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
				Gravity.RIGHT);
	}

}
