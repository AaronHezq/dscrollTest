package com.example.dscrolltest;

import com.example.dscrolltest.ObservableScrollView.ScrollViewListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class CopyOfMainActivity extends Activity {

	private ObservableScrollView scrollView;
	LinearLayout layout;
	private View cLine;
	private int cLineY = 0;
	private View localView;
	private int localViewHeight = 0;
	private int localViewY = 0;

	private int scrollY = -1;

	private boolean isTouching = false;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				if (isTouching)
					return;
				if (scrollView.getScrollY() == scrollY) {
					int h = localViewY + localViewHeight / 2 - cLineY;
					// scrollView.scrollBy(0, h);
					scroll(h);
				}
				break;
			case 2:
				double h = (Double) msg.obj;
				scroll(h);
				break;
			}
		};
	};

	public void scroll(final double h) {
		scrollView.scrollBy(0, (int)h);
		if (h > 1) {
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					Message msg = Message.obtain();
					msg.what = 2;
					msg.obj = h/10;
					handler.sendMessage(msg);
				}
			}, 30);
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		scrollView = (ObservableScrollView) findViewById(R.id.scrollview);
		layout = (LinearLayout) findViewById(R.id.layout);
		cLine = findViewById(R.id.centerLine);
		scrollView.setScrollViewListener(new ScrollViewListener() {
			@Override
			public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
				getChildView();
				scrollY = y;
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						handler.sendEmptyMessage(1);
					}
				}, 300);
			}
		});

		scrollView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					isTouching = true;
					break;
				case MotionEvent.ACTION_UP:
					isTouching = false;
					break;
				}
				return false;
			}
		});

	}

	@SuppressLint("NewApi")
	public void getChildView() {

		if (cLineY == 0) {
			final int[] location = new int[2];
			cLine.getLocationOnScreen(location);
			cLineY = location[1];
		}

		int count = layout.getChildCount();
		final int[] location = new int[2];
		for (int i = 0; i < count; i++) {
			View v = layout.getChildAt(i);
			v.getLocationOnScreen(location);
			int y = location[1];
			// System.out.println(">>" + i + ":" + y + "=" + v.getHeight());
			if (y < cLineY && y + v.getHeight() > cLineY + cLine.getHeight()) {
				// ToastUtil.showShortToast(this, ((TextView)
				// v).getText().toString());
				localViewHeight = v.getHeight();
				localViewY = y;
				localView = v;
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
