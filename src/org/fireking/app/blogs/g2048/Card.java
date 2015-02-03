package org.fireking.app.blogs.g2048;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout {

	private int num = 0;

	private TextView lable;

	public Card(Context context) {
		super(context);

		lable = new TextView(context);
		lable.setTextSize(32);
		lable.setBackgroundColor(0x33ffffff);
		lable.setGravity(Gravity.CENTER);

		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		lp.setMargins(10, 10, 0, 0);
		addView(lable, lp);

		setNum(num);

	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
		if (num <= 0) {
			lable.setText("");
		} else
			lable.setText(num + "");
	}

	@Override
	public boolean equals(Object obj) {
		Card other = (Card) obj;
		if (num != other.num)
			return false;
		return true;
	}

}
