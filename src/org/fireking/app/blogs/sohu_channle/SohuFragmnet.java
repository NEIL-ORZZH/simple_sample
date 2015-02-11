package org.fireking.app.blogs.sohu_channle;

import org.fireking.app.blogs.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class SohuFragmnet extends Fragment {

	private ListView normal_listview;
	private int width = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Bundle bundle = getArguments();
		int pos = bundle.getInt("pos", -1);
		width = bundle.getInt("mWidth");
		initView();

		if (pos != -1) {
			NormalListViewAdapter adapter = new NormalListViewAdapter(
					getActivity(), width);
			normal_listview.setAdapter(adapter);
		}
	}

	private void initView() {
		normal_listview = (ListView) getView().findViewById(
				R.id.normal_listview);
	}

}
