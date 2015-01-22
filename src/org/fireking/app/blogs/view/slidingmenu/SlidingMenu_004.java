package org.fireking.app.blogs.view.slidingmenu;

import org.fireking.app.blogs.R;

import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;

/**
 * 仿魅族max4的settings菜单侧滑效果
 * 
 * @author wanggang
 *
 */
public class SlidingMenu_004 extends HorizontalScrollView {

	// 内容界面
	private ViewGroup mContent;
	// 菜单界面
	private ViewGroup mMenuView;
	// 主包裹界面
	private ViewGroup mWapper;

	// 标记位，执行性一次
	private boolean isOnce;

	// 屏幕宽度
	private int mScreenWidth;
	// 菜单的宽度
	private int mMenuWidth;

	public SlidingMenu_004(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		// 获取WindowManager对象
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		// 计算屏幕的宽度
		DisplayMetrics metrice = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrice);
		mScreenWidth = metrice.widthPixels;
		// 设置默认的菜单拉伸宽度
		int defaultWidth = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 80, context.getResources()
						.getDisplayMetrics());
		// 获取自定义属性
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.SlidingMenu, defStyleAttr, 0);
		int count = a.getIndexCount();
		for (int i = 0; i < count; i++) {
			switch (a.getIndex(i)) {
			case R.styleable.SlidingMenu_MenuPadding:
				// 设置菜单的宽度
				mMenuWidth = mScreenWidth
						- (int) a.getDimension(a.getIndex(i), defaultWidth);
				break;
			}
		}
		a.recycle();
	}

	public SlidingMenu_004(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SlidingMenu_004(Context context) {
		this(context, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (!isOnce) {
			// 初始化view
			mWapper = (ViewGroup) getChildAt(0);
			mContent = (ViewGroup) mWapper.getChildAt(1);
			mMenuView = (ViewGroup) mWapper.getChildAt(0);
			// 初始化大小
			mContent.getLayoutParams().width = mMenuWidth;
			mMenuView.getLayoutParams().width = mMenuWidth;
			isOnce = false;
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			mMenuView.scrollTo(mMenuWidth, 0);
			mContent.scrollTo(mScreenWidth - mMenuWidth, 0);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		return super.onTouchEvent(ev);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		float scale = 1.0f * l / mMenuWidth; // 1~0
		ViewHelper.setTranslationX(mMenuView, scale * mMenuWidth);
	}

}
