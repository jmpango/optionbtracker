package org.meta.optionbtracker.adapters;

import org.meta.optionbtracker.Startup;
import org.meta.optionbtracker.fragments.ReportingTabFragment;
import org.meta.optionbtracker.fragments.StockTabFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MetaFragmentPagerAdapter extends FragmentStatePagerAdapter {

	private final int PAGE_COUNT = 3;
	private String entity;
	private Startup startup;

	public MetaFragmentPagerAdapter(Startup startup, FragmentManager fragmentManager, String entity) {
		super(fragmentManager);
		this.entity = entity;
		this.startup = startup;
	}

	public String getEntity() {
		return entity;
	}

	@Override
	public Fragment getItem(int arg0) {
		Bundle data = new Bundle();
		switch (arg0) {

		/** Android tab is selected */
		case 0:
			ReportingTabFragment reportingFragment = new ReportingTabFragment(startup, getEntity());
			data.putInt("current_page", arg0 + 1);
			reportingFragment.setArguments(data);
			return reportingFragment;

		case 1:
			StockTabFragment stockFragment = new StockTabFragment();
			data.putInt("current_page", arg0 + 1);
			stockFragment.setArguments(data);
			return stockFragment;
		}

		return null;
	}

	 public int getItemPosition(Object object) {
	       Fragment fragment = (Fragment) object;
	       
	       for(int i = 0; i < getCount(); i++) {
	            Fragment item = (Fragment) getItem(i);
	            if(item.equals(fragment)) {
	                return i;
	            }
	        }

	        return POSITION_NONE;
	    }
	
	@Override
	public int getCount() {
		return PAGE_COUNT;
	}

}
