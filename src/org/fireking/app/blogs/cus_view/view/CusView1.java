package org.fireking.app.blogs.cus_view.view;

import org.fireking.app.blogs.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class CusView1 extends View {

	// 方位布局

	private final int _LEFT = 0;
	private final int _RIGHT = 1;
	private final int _TOP = 2;
	private final int _BUTTOM = 3;
	private final int _CENTER = 4;
	private final int _CENTER_VERTICAL = 5;
	private final int _CENTER_HORIZONTAL = 6;

	// default value
	private final String DEFAULT_TEXT = "";
	private final int DEFAULT_TEXTCOLOR = 0xFF000000;
	private final int DEFAULT_TEXTSIZE = sp2px(16);
	private final int DEFAILT_BACKGROUNDCOLOR = 0xFFeeeeee;
	private final int DEFAULT_GRAVITY = 0;

	private String text = DEFAULT_TEXT;
	private int textSize = DEFAULT_TEXTSIZE;
	private int textColor = DEFAULT_TEXTCOLOR;
	private int backgroundColor = DEFAILT_BACKGROUNDCOLOR;
	private int gravity = DEFAULT_GRAVITY;

	Rect bound = new Rect();

	private Paint mPaint = new Paint();

	public CusView1(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		initView(context, attrs);
	}

	public CusView1(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CusView1(Context context) {
		this(context, null);
	}

	private void initView(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.Hy_customview1);
		text = a.getString(R.styleable.Hy_customview1_text);
		textSize = (int) a.getDimension(R.styleable.Hy_customview1_textSize,
				DEFAULT_TEXTSIZE);
		textColor = a.getColor(R.styleable.Hy_customview1_textColor,
				DEFAULT_TEXTCOLOR);
		backgroundColor = a.getColor(
				R.styleable.Hy_customview1_backgroundColor,
				DEFAILT_BACKGROUNDCOLOR);
		gravity = a.getInt(R.styleable.Hy_customview1_gravity, DEFAULT_GRAVITY);
		a.recycle();

		getGravity(gravity);

		mPaint.setAntiAlias(true);// 设置抗锯齿
		mPaint.setTextSize(textSize);
	}

	private void getGravity(int gravity2) {
		switch (gravity2) {
		case 0:
			gravity = _LEFT;
			break;
		case 1:
			gravity = _RIGHT;
			break;
		case 2:
			gravity = _TOP;
			break;
		case 3:
			gravity = _BUTTOM;
			break;
		case 4:
			gravity = _CENTER;
			break;
		case 5:
			gravity = _CENTER_VERTICAL;
			break;
		case 6:
			gravity = _CENTER_HORIZONTAL;
			break;
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		mPaint.getTextBounds(text, 0, text.length(), bound);

		int width = 0;
		// 如果设置了match_parent或者固定值，直接设置为当前的值
		if (widthMode == MeasureSpec.EXACTLY) {
			width = widthSize;
		} else {
			// 没设置大小的情况下，获取他的paddingleft + paddingright
			width = (int) (bound.width() + getPaddingLeft() + getPaddingRight());
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
			height = (int) (bound.height() + getPaddingTop() + getPaddingBottom());
			if (heightMode == MeasureSpec.AT_MOST) {
				height = Math.min(heightSize, height);
			}
		}

		// 将计划好的值设置进去
		setMeasuredDimension(width, height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 绘制背景
		mPaint.setColor(backgroundColor);
		Rect bg = new Rect(0, 0, getWidth(), getHeight());
		canvas.drawRect(bg, mPaint);

		drawText(canvas);
	}

	int x = 0;
	int y = 0;

	private void drawText(Canvas canvas) {
		mPaint.setColor(textColor);
		switch (gravity) {
		case _LEFT:
			y = bound.height();
			x = 0;
			break;
		case _RIGHT:
			x = getWidth() - bound.width();
			y = bound.height();
			break;
		case _TOP:
			x = 0;
			y = bound.height();
			break;
		case _BUTTOM:
			y = getHeight() - bound.height() / 2;
			x = 0;
			break;
		case _CENTER:
			x = getWidth() / 2 - bound.width() / 2;
			y = (getHeight() + bound.height()) / 2;
			break;
		case _CENTER_VERTICAL:
			y = getHeight() / 2 - bound.height() / 2;
			x = 0;
			break;
		case _CENTER_HORIZONTAL:
			x = getWidth() / 2 - bound.width() / 2;
			y = bound.height();
			break;
		}
		canvas.drawText(text, x, y, mPaint);
	}

	protected int sp2px(int value) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
				value, getResources().getDisplayMetrics());
	}
}
