package com.example.dscrolltest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class MainActivity2 extends Activity {

	private ListView listView;

	private View cLine;
	private int cLineY = 0;
	private View localView;
	private int localViewHeight = 0;
	private int localViewY = 0;

	private boolean isTouching = false;

	private Handler handler = new Handler() {
		@SuppressLint("NewApi")
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				if (isTouching)
					return;
				int h = localViewY + localViewHeight / 2 - cLineY;
				listView.smoothScrollBy(h, 800);
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		listView = (ListView) findViewById(R.id.listview);
		listView.setAdapter(new ListAdapter(this));
		cLine = findViewById(R.id.centerLine);
		listView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				getChildView();
				if(scrollState == SCROLL_STATE_IDLE) {
					handler.sendEmptyMessage(1);
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
			}
		});

		listView.setOnTouchListener(new OnTouchListener() {
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

		int count = listView.getChildCount();
		final int[] location = new int[2];
		for (int i = 0; i < count; i++) {
			View v = listView.getChildAt(i);
			v.getLocationOnScreen(location);
			int y = location[1];
			if (y < cLineY && y + v.getHeight() > cLineY + cLine.getHeight()) {
				localViewHeight = v.getHeight();
				localViewY = y;
				localView = v;
			}
		}
	}

}
