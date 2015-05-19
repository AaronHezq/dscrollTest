package com.example.dscrolltest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

	private HoldView holdView;
	private Context context;

	private int[] colors = new int[] { R.color.red, R.color.green, R.color.blue, R.color.yellow, R.color.orange, R.color.red,
			R.color.green, R.color.blue, R.color.yellow, R.color.orange };

	public ListAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return colors.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			holdView = new HoldView();
			convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
			holdView.textView = (TextView) convertView.findViewById(R.id.value);
			convertView.setTag(holdView);
		} else {
			holdView = (HoldView) convertView.getTag();
		}
		holdView.textView.setBackgroundColor(context.getResources().getColor(colors[position]));
		holdView.textView.setText(position + 1 + "");
		return convertView;
	}

	private class HoldView {
		TextView textView;
	}
}
