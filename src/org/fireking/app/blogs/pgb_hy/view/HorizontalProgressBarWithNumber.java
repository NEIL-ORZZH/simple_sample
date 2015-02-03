package org.fireking.app.blogs.pgb_hy.view;

import org.fireking.app.blogs.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

public class HorizontalProgressBarWithNumber extends ProgressBar {

	// default value

	private static final int DEFAULT_TEXT_SIZE = 10;
	private static final int DEFAULT_TEXT_COLOR = 0xFFFC00D1;
	private static final int DEFAULT_COLOR_UNREACHED_COLOR = 0xFFD3D6DA;
	private static final int DEFAULT_HEIGHT_REACHED_PROGRESS_BAR = 2;
	private static final int DEFAULT_HEIGHT_UNREACHED_PROGRESS_BAR = 2;
	private static final int DEFAULT_SIZE_TEXT_OFFSET = 10;

	// param and fileds

	protected Paint mPaint = new Paint();

	protected int mTextColor = DEFAULT_TEXT_COLOR;

	protected int mTextSize = sp2px(DEFAULT_TEXT_SIZE);

	protected int mTextOffset = dip2px(DEFAULT_SIZE_TEXT_OFFSET);

	protected int mReachedProgressbarHeight = dip2px(DEFAULT_HEIGHT_REACHED_PROGRESS_BAR);

	protected int mReachedBarColor = DEFAULT_TEXT_COLOR;

	protected int mUnReachedBarColor = DEFAULT_COLOR_UNREACHED_COLOR;

	protected int mUnReachedProgressBarHeight = dip2px(DEFAULT_HEIGHT_UNREACHED_PROGRESS_BAR);

	protected int mRealWidth;

	protected boolean mIfDrawText = true;

	protected static final int VISIBLE = 0;

	protected static final int INVISIBLE = 1;

	private Context mContext;

	public HorizontalProgressBarWithNumber(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		this.mContext = context;
	}

	public HorizontalProgressBarWithNumber(Context context) {
		this(context, null);
	}

	protected int dip2px(int dpvalue) {
		float mResult = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dpvalue, getResources().getDisplayMetrics());
		return (int) mResult;
	}

	protected int sp2px(int spvalue) {
		float mResult = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
				spvalue, getResources().getDisplayMetrics());
		return (int) mResult;
	}

	public HorizontalProgressBarWithNumber(Context context, AttributeSet attrs) {
		this(context, attrs, 0);

		setHorizontalScrollBarEnabled(true);

		obtainStyledAttributes(attrs);

		mPaint.setTextSize(mTextSize);
		mPaint.setColor(mTextColor);
	}

	/**
	 * get the styled attributes
	 * 
	 * @param attrs
	 */
	private void obtainStyledAttributes(AttributeSet attrs) {
		final TypedArray a = getContext().obtainStyledAttributes(attrs,
				R.styleable.HorizontalProgerssBarWithNumber);

		mTextColor = a
				.getColor(
						R.styleable.HorizontalProgerssBarWithNumber_progress_text_color,
						DEFAULT_TEXT_COLOR);

		mTextSize = (int) a.getDimension(
				R.styleable.HorizontalProgerssBarWithNumber_progress_text_size,
				DEFAULT_TEXT_SIZE);

		mReachedBarColor = a
				.getColor(
						R.styleable.HorizontalProgerssBarWithNumber_progerss_reached_color,
						DEFAULT_TEXT_COLOR);

		mUnReachedBarColor = a
				.getColor(
						R.styleable.HorizontalProgerssBarWithNumber_progress_unreached_color,
						DEFAULT_COLOR_UNREACHED_COLOR);

		mReachedProgressbarHeight = (int) a
				.getDimension(
						R.styleable.HorizontalProgerssBarWithNumber_progress_reached_bar_height,
						DEFAULT_HEIGHT_REACHED_PROGRESS_BAR);

		mUnReachedProgressBarHeight = (int) a
				.getDimension(
						R.styleable.HorizontalProgerssBarWithNumber_progress_unreached_bar_height,
						DEFAULT_HEIGHT_UNREACHED_PROGRESS_BAR);

		mTextOffset = (int) a
				.getDimension(
						R.styleable.HorizontalProgerssBarWithNumber_progress_text_offset,
						DEFAULT_SIZE_TEXT_OFFSET);

		int textVisible = a
				.getInt(R.styleable.HorizontalProgerssBarWithNumber_progress_text_visibility,
						VISIBLE);

		if (textVisible != VISIBLE) {
			textVisible = INVISIBLE;
		}
		a.recycle();
	}

	@Override
	protected synchronized void onMeasure(int widthMeasureSpec,
			int heightMeasureSpec) {

		int heightMode = MeasureSpec.getMode(heightMeasureSpec);

		if (heightMode != MeasureSpec.EXACTLY) {
			// baseline:字符的基准线，保持一行字符都在该水平线上，而字符高出或者底出水平线的部分就是descent 和 ascent
			// mPaint.descent()：是baseline到最低一个字符的高度
			// mPaint.ascent():是baseline到最高一个字符的高度
			// descent和ascent详细说明：
			// http://zhidao.baidu.com/link?url=egt9llTUZIpFjlF3dcCmP0qupNs5zEmxfPJD_Mt8QyNhTk5HlmscbosPUlq3ZDjZQNI8_fx8rmbLX7vlP2zeBq
			float textHeight = (mPaint.descent() + mPaint.ascent());
			int exceptHeight = (int) (getPaddingTop() + getPaddingBottom() + Math
					.max(Math.max(mReachedProgressbarHeight,
							mUnReachedProgressBarHeight), Math.abs(textHeight)));
			heightMeasureSpec = MeasureSpec.makeMeasureSpec(exceptHeight,
					MeasureSpec.EXACTLY);
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mRealWidth = w - getPaddingRight() - getPaddingLeft();
	}

	@Override
	protected synchronized void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.save();

		// 开始绘制的原点位置，为了让padding有效果，这里平移画笔
		canvas.translate(getPaddingLeft(), getHeight() / 2);

		boolean noNeedBg = false;

		// 当前进度和总进度的比例
		float radio = getProgress() * 1.0f / getMax();

		// 已经达到的宽度
		float progressPosx = mRealWidth * radio;

		String text = getProgress() + "%";

		// 计算字体的宽度
		float textWidth = mPaint.measureText(text);

		float textHeight = (mPaint.ascent() + mPaint.descent()) / 2;

		// 已经 到了最后了
		if (progressPosx + textWidth > mRealWidth) {
			progressPosx = mRealWidth - textWidth;
			noNeedBg = true;
		}

		// 绘制已到达的进度
		float endx = progressPosx - mTextOffset / 2;
		if (endx > 0) {
			mPaint.setColor(mReachedBarColor);
			mPaint.setStrokeWidth(mReachedProgressbarHeight);
			canvas.drawLine(0, 0, endx, 0, mPaint);
		}

		// 绘制文本
		if (mIfDrawText) {
			mPaint.setColor(mTextColor);
			canvas.drawText(text, progressPosx, -textHeight, mPaint);
		}

		if (!noNeedBg) {
			float start = progressPosx + mTextOffset / 2 + textWidth;
			mPaint.setColor(mUnReachedBarColor);
			mPaint.setStrokeWidth(mUnReachedProgressBarHeight);
			canvas.drawLine(start, 0, mRealWidth, 0, mPaint);
		}
		canvas.restore();
	}
}
