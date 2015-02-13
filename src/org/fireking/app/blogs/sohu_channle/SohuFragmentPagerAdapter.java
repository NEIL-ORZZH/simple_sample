package org.fireking.app.blogs.sohu_channle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class SohuFragmentPagerAdapter extends FragmentStatePagerAdapter {

	String[] titles;

	int width;

	public SohuFragmentPagerAdapter(FragmentManager fm, String[] titles,
			int width) {
		super(fm);
		this.titles = titles;
		this.width = width;
	}

	@Override
	public Fragment getItem(int position) {
		Fragment f = null;
		if (position == 1) {
			f = new AppStoreFragment();
		} else {
			f = new SohuFragmnet();
		}
		Bundle b = new Bundle();
		b.putString("mm", titles[position]);
		b.putInt("pos", position);
		b.putInt("mWidth", width);
		f.setArguments(b);
		return f;
	}

	@Override
	public int getCount() {
		return titles.length;
	}

}
