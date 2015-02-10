package org.fireking.app.blogs.cus_view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class CusView2 extends View {

	private final int DEFAULT_LEFT_COLOR = 0xFFFF0000;
	private final int DEFAULT_RIGHT_COLOR = 0xFF0000FF;
	private final int DEFAULT_CENTER_COLOR = 0xFFFF00FF;

	private int padding = dp2px(30);

	private final int DEFAULT_BG_COLOR = 0xFF334455;

	// 半径
	private int radius = dp2px(15);

	// 小球数
	private int count = 3;

	private Paint mPaint = new Paint();

	public CusView2(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

	}

	public CusView2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CusView2(Context context) {
		this(context, null);
	}

	Runnable runnable = new Runnable() {

		@Override
		public void run() {
			invalidate();
			handler.sendMessageDelayed(handler.obtainMessage(1), 1500);
		}
	};

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:

				isT = false;
				handler.post(runnable);
				break;

			default:
				break;
			}
		};
	};

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		int width = 0;
		// 如果设置了match_parent或者固定值，直接设置为当前的值
		if (widthMode == MeasureSpec.EXACTLY) {
			width = widthSize;
		} else {
			// 没设置大小的情况下，获取他的paddingleft + paddingright + 固定的padding
			width = (int) (radius * 2 * count + getPaddingLeft()
					+ getPaddingRight() + (count + 1) * padding);
			// 如果设置的为wrap_content
			if (widthMode == MeasureSpec.AT_MOST) {
				width = Math.min(heightSize, width);
			}
		}

		int height = 0;
		// 同上的width计算
		if (heightMode == MeasureSpec.EXACTLY) {
			height = heightSize;
		} else {
			height = (int) (radius * 2 * count + getPaddingTop()
					+ getPaddingBottom() + (count + 1) * padding);
			if (heightMode == MeasureSpec.AT_MOST) {
				height = Math.min(heightSize, height);
			}
		}

		// 将计划好的值设置进去
		setMeasuredDimension(width, height);
	}

	int left_s = padding;
	int left_e = 3 * padding;

	int right_s = 0;
	int right_e = 0;

	boolean isT = false;

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// 绘制整个的布局边界
		mPaint.setColor(DEFAULT_BG_COLOR);
		Rect r = new Rect(0, 0, getWidth(), getHeight());
		canvas.drawRect(r, mPaint);

		// 绘制左边小球
		mPaint.setColor(DEFAULT_LEFT_COLOR);
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Style.FILL);
		mPaint.setStrokeCap(Cap.ROUND);
		mPaint.setStrokeWidth(radius);
		if (!isT) {
			canvas.drawCircle(left_s, padding, radius, mPaint);
		} else {
			canvas.drawCircle(padding * 3, padding, radius, mPaint);
		}

		// 绘制中心小球
		mPaint.setColor(DEFAULT_CENTER_COLOR);
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Style.FILL);
		mPaint.setStrokeCap(Cap.ROUND);
		mPaint.setStrokeWidth(radius);
		if (!isT) {
			canvas.drawCircle(padding * 3, padding, radius, mPaint);
		} else {
			canvas.drawCircle(padding * 5, padding, radius, mPaint);
		}

		// 绘制右边小球
		mPaint.setColor(DEFAULT_RIGHT_COLOR);
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Style.FILL);
		mPaint.setStrokeCap(Cap.ROUND);
		mPaint.setStrokeWidth(radius);
		if (!isT) {
			canvas.drawCircle(padding * 5, padding, radius, mPaint);
		} else {
			canvas.drawCircle(left_s, padding, radius, mPaint);
		}

		isT = true;

		handler.post(runnable);
	}

	protected int dp2px(int value) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				value, getResources().getDisplayMetrics());
	}

}
