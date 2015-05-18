package com.example.dscrolltest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ScrollView scrollView;
	LinearLayout layout;
	private View cLine;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		scrollView = (ScrollView) findViewById(R.id.scrollview);
		layout = (LinearLayout) findViewById(R.id.layout);
		cLine = findViewById(R.id.centerLine);
		scrollView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				final int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_MOVE:
					layout.invalidate();
					getChildView();
					break;
				}
				return false;
			}
		});

	}

	@SuppressLint("NewApi") public View getChildView() {
		int count = layout.getChildCount();
		for (int i = 0; i < count; i++) {
			View v = layout.getChildAt(i);
			if(v.getY()<cLine.getY()&&v.getY()+v.getHeight()>cLine.getY()+cLine.getHeight()) {
				ToastUtil.showShortToast(this, ((TextView)v).getText().toString());
				return v;
			}
		}
		return null;
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
