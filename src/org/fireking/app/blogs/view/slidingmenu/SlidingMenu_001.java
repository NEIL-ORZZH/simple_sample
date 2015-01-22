package org.fireking.app.blogs.view.slidingmenu;

import org.fireking.app.blogs.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;

public class SlidingMenu_001 extends HorizontalScrollView {

	private ViewGroup mContent;
	private ViewGroup mMenu;
	private ViewGroup mWapper;

	private int mScrollWidth;
	private int mMenuWidtth;

	// dip
	private int PadingRight;

	private int mScrollX;

	// 让计算只执行一次
	private boolean once;

	// 判断是否打开// 默认关闭
	private boolean isOpen = false;

	public SlidingMenu_001(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);

		mScrollWidth = outMetrics.widthPixels;

		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.SlidingMenu, defStyleAttr, 0);
		int index = a.getIndexCount();
		for (int i = 0; i < index; i++) {
			switch (a.getIndex(i)) {
			case R.styleable.SlidingMenu_MenuPadding:
				PadingRight = (int) a.getDimension(i, (int) TypedValue
						.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50,
								context.getResources().getDisplayMetrics()));
				break;
			}
		}
		// 回收
		a.recycle();
		// 设置默认的宽度
	}

	public SlidingMenu_001(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SlidingMenu_001(Context context) {
		this(context, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		if (!once) {
			mWapper = (ViewGroup) getChildAt(0);
			mMenu = (ViewGroup) mWapper.getChildAt(0);
			mContent = (ViewGroup) mWapper.getChildAt(1);

			// 设置宽度
			mMenuWidtth = mMenu.getLayoutParams().width = mScrollWidth
					- PadingRight;
			mContent.getLayoutParams().width = mScrollWidth;
			mWapper.getLayoutParams().width = mScrollWidth;
			once = true;
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			this.scrollTo(mMenuWidtth, 0);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		mScrollX = getScrollX();
		switch (ev.getAction()) {
		case MotionEvent.ACTION_UP:
			if (mScrollX > mMenuWidtth / 2) {
				// 菜单全部隐藏
				smoothScrollTo(mMenuWidtth, 0);
			} else {
				// 菜单全部显示
				smoothScrollTo(0, 0);
			}
			return true;
		}
		return super.onTouchEvent(ev);
	}

	/**
	 * @Name openMenu
	 * @Description 打开按钮
	 * @return void
	 * @Author join
	 * @Date 2015年1月20日 下午10:06:21
	 *
	 */
	public void openMenu() {
		if (!isOpen) {
			this.smoothScrollTo(0, 0);
			isOpen = true;
		}
	}

	/**
	 * @Name closeMenu
	 * @Description 关闭按钮
	 * @return void
	 * @Author join
	 * @Date 2015年1月20日 下午10:06:38
	 *
	 */
	public void closeMenu() {
		if (isOpen) {
			this.smoothScrollTo(mMenuWidtth, 0);
			isOpen = false;
		}
	}

	/**
	 * @Name toggle
	 * @Description 切换按钮
	 * @return void
	 * @Author join
	 * @Date 2015年1月20日 下午10:06:46
	 *
	 */
	public void toggle() {
		if (isOpen) {
			closeMenu();
		} else {
			openMenu();
		}
	}

}
