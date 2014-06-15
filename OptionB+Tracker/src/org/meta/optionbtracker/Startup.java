package org.meta.optionbtracker;

import java.util.ArrayList;

import org.meta.optionbtracker.adapters.MetaFragmentPagerAdapter;
import org.meta.optionbtracker.async.AsyncPipe;
import org.meta.optionbtracker.async.impl.AsyncPipeImpl;
import org.meta.optionbtracker.utils.Contants;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

@SuppressLint("NewApi")
public class Startup extends FragmentActivity implements TabListener {

	private ActionBar actionBar = null;
	public ViewPager viewPager = null;
	public FragmentManager fragmentManager = null;
	private Tab tab = null;
	private AsyncPipe serverData = null;
	private TextView appHeader = null;

	public String level = Contants.DEFAULT_ENTITY;
	public String weekNo = Contants.EMPTY;
	public String ipORDistrict = Contants.EMPTY;
	public String appHeaderText = Contants.DEFAULT_APP_HEADER;

	private SimpleOnPageChangeListener pageChangeListener;
	public MetaFragmentPagerAdapter fragmentPagerAdapter;
	private Spinner ipORDistrictDropDown;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup);

		serverData = new AsyncPipeImpl();

		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		appHeader = (TextView) findViewById(R.id.appHeader);
		viewPager = (ViewPager) findViewById(R.id.pager);

		this.tab = actionBar.newTab().setText(Contants.REPORTING_TAB)
				.setTabListener(this);
		actionBar.addTab(tab);

		this.tab = actionBar.newTab().setText(Contants.STOCK_TAB)
				.setTabListener(this);
		actionBar.addTab(tab);
		actionBar.setDisplayShowTitleEnabled(true);

		fragmentManager = getSupportFragmentManager();

		pageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				super.onPageSelected(position);
				actionBar.setSelectedNavigationItem(position);
			}
		};

		viewPager.setOnPageChangeListener(pageChangeListener);

		setAppHeaderText("National ~ " + Contants.DEFAULT_APP_HEADER + " ~ "
				+ serverData.getCurrentWeek());

		loadGUI();

	}

	public void loadGUI() {
		appHeader.setText(getAppHeaderText());

		fragmentPagerAdapter = new MetaFragmentPagerAdapter(this, fragmentManager,
				getLevel());

		viewPager.setAdapter(fragmentPagerAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.startup, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_filter:
			displayFilterGUI();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@SuppressWarnings("unchecked")
	private void displayFilterGUI() {
		final Dialog filterDialog = new Dialog(Startup.this,
				R.style.CenterDialog);
		filterDialog.setContentView(R.layout.dialog_filter);
		filterDialog.setCancelable(true);

		ArrayList<String> weekNoList = (ArrayList<String>) serverData
				.getWeeks();
		ArrayList<String> levelList = (ArrayList<String>) serverData
				.getLevels();
		ArrayList<String> ipORDistrictList = new ArrayList<String>();

		ipORDistrictList.add("Uganda");

		final Spinner weekDropDown = (Spinner) filterDialog
				.findViewById(R.id.week_filter_dropdown);
		final Spinner levelDropDown = (Spinner) filterDialog
				.findViewById(R.id.level_filter_dropdown);
		ipORDistrictDropDown = (Spinner) filterDialog
				.findViewById(R.id.ip_district_filter_dropdown);

		ArrayAdapter<String> weekNoAdapter = new ArrayAdapter<String>(
				Startup.this, android.R.layout.simple_spinner_item, weekNoList);
		ArrayAdapter<String> levelAdapter = new ArrayAdapter<String>(
				Startup.this, android.R.layout.simple_spinner_item, levelList);
		ArrayAdapter<String> ipORDistrictAdapter = new ArrayAdapter<String>(
				Startup.this, android.R.layout.simple_spinner_item,
				ipORDistrictList);

		weekDropDown.setAdapter(weekNoAdapter);
		levelDropDown.setAdapter(levelAdapter);
		ipORDistrictDropDown.setAdapter(ipORDistrictAdapter);

		weekDropDown
				.setOnItemSelectedListener(new WeekNoItemSelectedListener());
		levelDropDown
				.setOnItemSelectedListener(new LevelItemSelectedListener());
		ipORDistrictDropDown
				.setOnItemSelectedListener(new IpORDistrictItemSelectedListener());

		Button filterButton = (Button) filterDialog
				.findViewById(R.id.filter_btn_filter);
		filterButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				setAppHeaderText(getLevel() +  " ~ " + getIpORDistrict() + " ~ " + getWeekNo());
				loadGUI();
				filterDialog.dismiss();
			}

		});

		filterDialog.show();
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {

	}

	public class WeekNoItemSelectedListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			setWeekNo(arg0.getItemAtPosition(arg2).toString());
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {

		}

	}
	
	@SuppressWarnings("unchecked")
	public class LevelItemSelectedListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String Level = arg0.getItemAtPosition(arg2).toString();
			setLevel(Level);
			
			ArrayList<String> ipORDistrictList = (ArrayList<String>) serverData.getLevelEntities(getLevel());
			ArrayAdapter<String> ipORDistrictAdapter = new ArrayAdapter<String>(
					Startup.this, android.R.layout.simple_spinner_item,
					ipORDistrictList);
			ipORDistrictDropDown.setAdapter(ipORDistrictAdapter);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {

		}

	}

	public class IpORDistrictItemSelectedListener implements
			OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			setIpORDistrict(arg0.getItemAtPosition(arg2).toString());
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {

		}

	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getWeekNo() {
		return weekNo;
	}

	public void setWeekNo(String weekNo) {
		this.weekNo = weekNo;
	}

	public String getIpORDistrict() {
		return ipORDistrict;
	}

	public void setIpORDistrict(String ipORDistrict) {
		this.ipORDistrict = ipORDistrict;
	}

	public String getAppHeaderText() {
		if (appHeaderText == null)
			appHeaderText = Contants.DEFAULT_APP_HEADER;

		return appHeaderText;
	}

	public void setAppHeaderText(String appHeaderText) {
		this.appHeaderText = appHeaderText;
	}
}
