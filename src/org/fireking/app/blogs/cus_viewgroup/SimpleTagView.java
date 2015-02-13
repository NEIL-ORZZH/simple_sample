package org.fireking.app.blogs.cus_viewgroup;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class SimpleTagView extends LinearLayout {

	private List<String> datas;

	public SimpleTagView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setOrientation(VERTICAL);
		initView();
	}

	private void initView() {
	}

	public void setAdapter(List<String> _datas) {
		datas = _datas;
		invalidate();
	}

	public SimpleTagView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SimpleTagView(Context context) {
		this(context, null);
	}

	private int height = 0;

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		
		if (widthMode == MeasureSpec.EXACTLY) {

		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
	}
}
