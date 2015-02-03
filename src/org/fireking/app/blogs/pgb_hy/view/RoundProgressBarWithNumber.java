package org.fireking.app.blogs.pgb_hy.view;

import org.fireking.app.blogs.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;

public class RoundProgressBarWithNumber extends HorizontalProgressBarWithNumber {

	private int mRadius = dip2px(30);

	public RoundProgressBarWithNumber(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public RoundProgressBarWithNumber(Context context) {
		this(context, null);
	}

	public RoundProgressBarWithNumber(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		mReachedProgressbarHeight = (int) (mUnReachedProgressBarHeight * 2.5f);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.RoundProgressBarWithNumber);

		mRadius = (int) a.getDimension(
				R.styleable.RoundProgressBarWithNumber_radius, mRadius);

		a.recycle();

		mTextSize = sp2px(14);

		mPaint.setStyle(Style.STROKE);
		mPaint.setAntiAlias(true);// 防锯齿
		mPaint.setDither(true);// 防抖动
		mPaint.setStrokeCap(Cap.ROUND);// 画笔画刷类型
	}

	@Override
	protected synchronized void onMeasure(int widthMeasureSpec,
			int heightMeasureSpec) {
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int paintWidth = Math.max(mReachedProgressbarHeight,
				mUnReachedProgressBarHeight);
		if (heightMode != MeasureSpec.EXACTLY) {
			int exceptHeight = (int) (getPaddingTop() + getPaddingBottom()
					+ mRadius * 2 + paintWidth);
			heightMeasureSpec = MeasureSpec.makeMeasureSpec(exceptHeight,
					MeasureSpec.EXACTLY);
		}

		if (widthMode != MeasureSpec.EXACTLY) {
			int exceptWidth = (int) (getPaddingLeft() + getPaddingRight()
					+ mRadius * 2 + paintWidth);
			widthMeasureSpec = MeasureSpec.makeMeasureSpec(exceptWidth,
					MeasureSpec.EXACTLY);
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected synchronized void onDraw(Canvas canvas) {

		String text = getProgress() + "%";
		float textWidth = mPaint.measureText(text);
		float textHeight = (mPaint.descent() + mPaint.ascent()) / 2;

		canvas.save();
		canvas.translate(getPaddingLeft(), getPaddingTop());
		mPaint.setStyle(Style.STROKE);

		mPaint.setColor(mUnReachedBarColor);
		mPaint.setStrokeWidth(mUnReachedProgressBarHeight);
		canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);

		mPaint.setColor(mReachedBarColor);
		mPaint.setStrokeWidth(mReachedProgressbarHeight);
		float sweepAngle = getProgress() * 1.0f / getMax() * 360;
		canvas.drawArc(new RectF(0, 0, mRadius * 2, mRadius * 2), 0,
				sweepAngle, false, mPaint);

		mPaint.setStyle(Style.FILL);
		canvas.drawText(text, mRadius - textWidth / 2, mRadius - textHeight,
				mPaint);
		canvas.restore();
	}
}
