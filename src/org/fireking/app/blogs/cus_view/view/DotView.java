package org.fireking.app.blogs.cus_view.view;

import org.fireking.app.blogs.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class DotView extends View {

	private int raduis = 25;

	private int textColor = 0xffff4500;

	public DotView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.Hy_dotview);
		textColor = a.getColor(R.styleable.Hy_dotview_dotview, textColor);
		a.recycle();
	}

	public DotView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DotView(Context context) {
		this(context, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);

		int width = 0;
		if (widthMode == MeasureSpec.EXACTLY) {
			width = widthSize;
		} else {
			width = raduis * 2 + getPaddingLeft() * 2;
			if (widthMode == MeasureSpec.AT_MOST) {
				width = Math.min(width, widthSize);
			}
		}
		setMeasuredDimension(width, width);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		Paint mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(textColor);
		mPaint.setStrokeCap(Cap.ROUND);
		mPaint.setStyle(Style.FILL);
		canvas.drawCircle(getWidth() / 2 - raduis, getHeight() / 2 - raduis,
				raduis, mPaint);

	}
}
