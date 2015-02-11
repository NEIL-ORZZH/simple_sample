package org.fireking.app.blogs.sohu_channle;

import org.fireking.app.blogs.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class SoHuChannle extends FragmentActivity {

	protected HorizontalScrollView mHorizontalScrollView;

	protected RadioGroup mRadioGroup;

	protected View gapLine;

	protected LazyLoadViewPager vPager;

	protected String[] titles = new String[] { "本周热播", "天天看大片", "末世情缘",
			"走进罪恶之城", "挑战智商佳片集", "英雄拯救世界", "爱与欲的碰撞", "高成本大制作", "影院片库" };

	protected LayoutInflater inflater;

	protected WindowManager wm;

	protected int width = 0;

	protected int gapLineHeight = 4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sohu_channle);

		mHorizontalScrollView = (HorizontalScrollView) this
				.findViewById(R.id.horizontal_scrollview);
		mRadioGroup = (RadioGroup) this.findViewById(R.id.radio_group);
		gapLine = this.findViewById(R.id.gap_line);

		vPager = (LazyLoadViewPager) this.findViewById(R.id.vPager);

		inflater = LayoutInflater.from(this);

		wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);

		width = metrics.widthPixels;

		initRadioGroup();
		mHorizontalScrollView.scrollBy(0, 0);

	}

	private void initRadioGroup() {
		mRadioGroup.removeAllViews();
		for (int i = 0; i < titles.length; i++) {
			RadioButton mRadioButton = getRadioChildView();
			mRadioButton.setText(titles[i]);
			mRadioButton.setTag(i);
			mRadioButton.setId(i);// 设置id，方便后面使用
			mRadioGroup.addView(mRadioButton);
		}

		mRadioGroup.setOnCheckedChangeListener(listener);

		mRadioGroup.getChildAt(0).performClick();
	}

	public void onWindowFocusChanged(boolean hasFocus) {
		if (hasFocus) {
			RadioButton b = (RadioButton) mRadioGroup.getChildAt(0);
			int radioWidth = b.getMeasuredWidth();
			gapLine.setLayoutParams(new LinearLayout.LayoutParams(radioWidth,
					gapLineHeight));
		}
	};

	OnCheckedChangeListener listener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			System.out.println("Item checkedId:" + checkedId);
			RadioButton btn = (RadioButton) group.getChildAt(checkedId);
			int radioWidth = btn.getMeasuredWidth();
			System.out.println("Item radioWidth:" + radioWidth);
			gapLine.setLayoutParams(new LinearLayout.LayoutParams(radioWidth,
					gapLineHeight));
			vPager.setCurrentItem(checkedId);
		}
	};

	private RadioButton getRadioChildView() {
		return (RadioButton) inflater.inflate(R.layout.text, null);
	}

}
