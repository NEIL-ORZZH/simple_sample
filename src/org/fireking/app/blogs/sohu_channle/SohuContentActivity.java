package org.fireking.app.blogs.sohu_channle;

import org.fireking.app.blogs.sohu_channle.LazyLoadViewPager.OnPageChangeListener;

import android.os.Bundle;
import android.widget.RadioButton;

public class SohuContentActivity extends SoHuChannle {

	private SohuFragmentPagerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initViewPager();
	}

	private void initViewPager() {
		adapter = new SohuFragmentPagerAdapter(getSupportFragmentManager(),
				titles, width);
		vPager.setAdapter(adapter);
		vPager.setOffscreenPageLimit(1);
		vPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				mRadioGroup.getChildAt(position).performClick();
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				RadioButton radioButton = (RadioButton) mRadioGroup
						.getChildAt(position);
				int _width = radioButton.getWidth();
				int x = (int) radioButton.getX();
				int y = (int) radioButton.getY();
				System.out.println("Item X :" + x + ", y :" + y + ", _width :"
						+ _width + ", swidth:" + width);

				mHorizontalScrollView.smoothScrollTo(x - _width, 0);
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
	}
}
