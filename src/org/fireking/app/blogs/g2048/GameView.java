package org.fireking.app.blogs.g2048;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

@SuppressLint("ClickableViewAccessibility")
public class GameView extends GridLayout {

	private Card[][] cards = new Card[4][4];

	public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		initView();
	}

	@SuppressLint("ClickableViewAccessibility")
	private void initView() {

		setBackgroundColor(Color.parseColor("#BBADA0"));
		this.setOnTouchListener(new OnTouchListener() {

			private float startX;
			private float startY;
			private float offsetX;
			private float offsetY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					startX = event.getX();
					startY = event.getY();
					break;
				case MotionEvent.ACTION_UP:
					offsetX = event.getX() - startX;
					offsetY = event.getY() - startY;

					if (Math.abs(offsetX) > Math.abs(offsetY)) {
						if (offsetX > 5) {
							// 右边
							right();
						} else if (offsetX < -5) {
							// 左边
							left();
						}
					} else {
						if (offsetY > 5) {
							// 下边
							down();
						} else if (offsetY < -5) {
							// 上边
							up();
						}
					}
					break;
				}
				return true;
			}
		});
	}

	private void left() {
		System.out.println("Left");
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {

				for (int x1 = x + 1; x1 < 4; x1++) {
					if (cards[x1][y].getNum() == 0) {
						if (cards[x][y].getNum() <= 0) {
							cards[x][y].setNum(cards[x1][y].getNum());
							cards[x1][y].setNum(0);
							x--;
							break;
						} else if (cards[x][y].equals(cards[x1][y])) {
							cards[x][y].setNum(cards[x][y].getNum() * 2);
							cards[x1][y].setNum(0);
							break;
						}
					}
				}
			}
		}
	}

	private void right() {
		System.out.println("Right");
	}

	private void up() {
		System.out.println("Up");
	}

	private void down() {
		System.out.println("down");
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		int cardWidth = (w - 10) / 4;
		addCard(cardWidth, cardWidth);

		startGame();
	}

	private void addCard(int cardWidth, int cardHeight) {
		Card card;
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				card = new Card(getContext());
				addView(card, cardWidth, cardHeight);

				cards[x][y] = card;
			}
		}
	}

	private void addRandomNum() {
		emptyPosint.clear();
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (cards[x][y].getNum() == 0)
					emptyPosint.add(new Point(x, y));
			}
		}
		Point p = emptyPosint
				.remove((int) (Math.random() * emptyPosint.size()));
		cards[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
	}

	private void startGame() {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				cards[x][y].setNum(0);
			}
		}
		addRandomNum();
		addRandomNum();
		addRandomNum();
		addRandomNum();
		addRandomNum();
		addRandomNum();
		addRandomNum();
		addRandomNum();
	}

	private List<Point> emptyPosint = new ArrayList<Point>();

	public GameView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GameView(Context context) {
		this(context, null);
	}

}
