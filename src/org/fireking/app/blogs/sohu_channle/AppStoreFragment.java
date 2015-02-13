package org.fireking.app.blogs.sohu_channle;

import org.fireking.app.blogs.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.RadioGroup;

public class AppStoreFragment extends Fragment {

	private ListView app_detail_list;

	private MyMergeAdapter adapter;
	private LayoutInflater inflater;
	private RadioGroup radio_group;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		inflater = LayoutInflater.from(getActivity());
		app_detail_list = (ListView) getView().findViewById(
				R.id.app_detail_list);
		adapter = new MyMergeAdapter();
		adapter.addView(getHeaderView());
		adapter.addView(getViewPager());
		app_detail_list.setAdapter(adapter);

		app_detail_list.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				System.out.println("Item :" + radio_group.getY());
			}
		});
	}

	private View getViewPager() {
		View view = inflater.inflate(R.layout.vpager, null);
		radio_group = (RadioGroup) view.findViewById(R.id.radio_group);
		return view;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.appstore, null);
	}

	private View getHeaderView() {
		return inflater.inflate(R.layout.header_view, null);
	}

}
