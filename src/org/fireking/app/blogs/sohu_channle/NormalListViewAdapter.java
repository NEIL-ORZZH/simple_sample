package org.fireking.app.blogs.sohu_channle;

import org.fireking.app.blogs.R;
import org.fireking.app.blogs.tools.Utility;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NormalListViewAdapter extends CommAdapter<String> {
	Context mContext;

	private int width = 0;

	private int cellWidth = 0;

	public NormalListViewAdapter(Context context, int width) {
		this.mContext = context;
		this.width = width;
		cellWidth = (width - 4 * Utility.dip2px(context, 8)) / 3;
	}

	@Override
	public int getCount() {
		// return list().size() % 3 > 0 ? list().size() / 3 + 1
		// : list().size() / 3;
		return 10;
	}

	@Override
	public View getCinvertView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(mContext, R.layout.normal_listview_item,
					null);
			holder.imly_1 = (LinearLayout) convertView
					.findViewById(R.id.normal_item_ly1);
			holder.imly_2 = (LinearLayout) convertView
					.findViewById(R.id.normal_item_ly2);
			holder.imly_3 = (LinearLayout) convertView
					.findViewById(R.id.normal_item_ly3);
			holder.im1 = (ImageView) convertView.findViewById(R.id.im1);
			holder.im1.setLayoutParams(new FrameLayout.LayoutParams(cellWidth,
					(int) (cellWidth * 1.37)));
			holder.im2 = (ImageView) convertView.findViewById(R.id.im2);
			holder.im2.setLayoutParams(new FrameLayout.LayoutParams(cellWidth,
					(int) (cellWidth * 1.37)));
			holder.im3 = (ImageView) convertView.findViewById(R.id.im3);
			holder.im3.setLayoutParams(new FrameLayout.LayoutParams(cellWidth,
					(int) (cellWidth * 1.37)));
			holder.im_t1 = (TextView) convertView.findViewById(R.id.im_t1);
			holder.im_t1.setLayoutParams(new RelativeLayout.LayoutParams(
					cellWidth, RelativeLayout.LayoutParams.WRAP_CONTENT));
			holder.im_t2 = (TextView) convertView.findViewById(R.id.im_t2);
			holder.im_t2.setLayoutParams(new RelativeLayout.LayoutParams(
					cellWidth, RelativeLayout.LayoutParams.WRAP_CONTENT));
			holder.im_t3 = (TextView) convertView.findViewById(R.id.im_t3);
			holder.im_t3.setLayoutParams(new RelativeLayout.LayoutParams(
					cellWidth, RelativeLayout.LayoutParams.WRAP_CONTENT));
			holder.im_c1 = convertView.findViewById(R.id.im1_c1);
			holder.im_c1.setLayoutParams(new FrameLayout.LayoutParams(
					cellWidth, (int) (cellWidth * 1.37)));
			holder.im_c2 = convertView.findViewById(R.id.im1_c2);
			holder.im_c2.setLayoutParams(new FrameLayout.LayoutParams(
					cellWidth, (int) (cellWidth * 1.37)));
			holder.im_c3 = convertView.findViewById(R.id.im1_c3);
			holder.im_c3.setLayoutParams(new FrameLayout.LayoutParams(
					cellWidth, (int) (cellWidth * 1.37)));
			holder.im_title1 = (TextView) convertView
					.findViewById(R.id.im1_title1);
			holder.im_title2 = (TextView) convertView
					.findViewById(R.id.im1_title2);
			holder.im_title3 = (TextView) convertView
					.findViewById(R.id.im1_title3);
			holder.im_desc1 = (TextView) convertView
					.findViewById(R.id.im1_desc1);
			holder.im_desc2 = (TextView) convertView
					.findViewById(R.id.im1_desc2);
			holder.im_desc3 = (TextView) convertView
					.findViewById(R.id.im1_desc3);
		}
		return convertView;
	}

	static class ViewHolder {
		public LinearLayout imly_1;
		public ImageView im1;
		public TextView im_t1;
		public View im_c1;
		public TextView im_title1;
		public TextView im_desc1;
		public LinearLayout imly_2;
		public ImageView im2;
		public TextView im_t2;
		public View im_c2;
		public TextView im_title2;
		public TextView im_desc2;
		public LinearLayout imly_3;
		public ImageView im3;
		public TextView im_t3;
		public View im_c3;
		public TextView im_title3;
		public TextView im_desc3;
	}

}
